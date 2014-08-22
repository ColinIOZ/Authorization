package com.big.authorization.cas;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

//import com.sun.istack.NotNull;
/*
 * 自定义查询数据库验证Handler,只需在deployerConfigContext.xml中的bean节点中引用该类即可
 */
public class UCQueryDatabaseAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler {

//	@NotNull
	private String sql;
//	@NotNull
//	private String sqlForMore;

	protected final boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		final String id = getPrincipalNameTransformer().transform(
				credentials.getUsername());
		final String password = credentials.getPassword();
		final String encryptedPassword = this.getPasswordEncoder().encode(
				password);

		try {
			/**   
		     * 第一个参数为执行sql   
		     * 第二个参数为参数类型 
		     * 第三个参数为参数数据    
		     */
			final String dbPassword = getJdbcTemplate().queryForObject(
					this.sql, String.class, id, id);
			
//			String email = (String)getJdbcTemplate().queryForObject(this.sqlForMore, String.class, new Object[] { id });
//		    credentials.setUsername(email);
		    
			return dbPassword.equals(encryptedPassword);
		} catch (final IncorrectResultSizeDataAccessException e) {
			// this means the username was not found.
			return false;
		}
	}

	/**
	 * @param sql
	 *            The sql to set.
	 */
	public void setSql(final String sql) {
		this.sql = sql;
	}
//	public void setSqlForMore(String sqlForMore) {
//		this.sqlForMore = sqlForMore;
//	}
}