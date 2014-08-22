/***********************************************************************
 * FileName:  AuthorizedWebapiDaoImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebapiDao which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;

import java.util.List;


import com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
import com.big.authorization.po.AuthorizedWebapi;


/** CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * Project: authorization
 * Module ID:
 * Comments:
 * JDK version used: <JDK1.7>
 * Namespace: <命名空间>
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified By：
 * Modified Date:
 * Why & What is modified:
 * Version: 0.1.0
 * 
 */
/**
 * @author huan
 *
 */
public class AuthorizedWebapiDaoImpl implements AuthorizedWebapiDaoI {
	private HibernateBaseDao basedao;

	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI#authorizedWebapiCount(java.lang.Object[])
	 */
	@Override
	public int authorizedWebapiCount(Object[] params) {
		StringBuilder hql = new StringBuilder("from AuthorizedWebapi aw inner join fetch aw.user inner join fetch aw.webapi ");
		if(params != null && "userid".equals(params[0])){
			params = new Object[]{params[1]};
			hql.append("where aw.user.id=? ");
		}
		if(params != null && "isauthorized".equals(params[0])){
			params = new Object[]{params[1]};
			hql.append("where aw.isauthorized=? ");
		}
		return basedao
				.getCountWithHql(hql.toString(), params);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI#authorizedWebapiList(java.lang.Object[], int, int)
	 */
	@Override
	public List authorizedWebapiList(Object[] params, int firstResult,
			int maxResults) {
		StringBuilder hql = new StringBuilder("from AuthorizedWebapi aw inner join fetch aw.user inner join fetch aw.webapi ");
		if(params != null && "userid".equals(params[0])){
			params = new Object[]{params[1]};
			hql.append("where aw.user.id=? ");
		}
		if(params != null && "isauthorized".equals(params[0])){
			params = new Object[]{params[1]};
			hql.append("where aw.isauthorized=? ");
		}
		//hql.append("order by aw.isauthorized");
		return basedao
				.queryForListWithHqlByPage(hql.toString(), params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI#getAuthorizedWebByWebapiId(java.lang.String)
	 */
	@Override
	public Object getAuthorizedWebByWebapiId(String webapiId, String userid) {

		return basedao
				.queryForObjectWithHql(
						"from AuthorizedWebapi aw inner join fetch aw.user inner join fetch aw.webapi where aw.webapi.id=? and aw.user.id=?",
						new Object[] { webapiId, userid });
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI#applyForWebapiAuthorisation(com.big.authorization.po.AuthorizedWebapi, java.lang.String, java.lang.String)
	 */
	@Override
	public int applyForWebapiAuthorisation(AuthorizedWebapi authorizedWebapi,
			String webapiId, String userid) {

		String sql = "insert into authorized_webapi(id,user_id,webapi_id,isauthorized,host_url,limit_request,remark) values(?,?,?,?,?,?,?)";
		return basedao.executeUpdateWithSql(
				sql,
				new Object[] { authorizedWebapi.getId(), userid, webapiId,
						authorizedWebapi.getIsauthorized(),
						authorizedWebapi.getHostUrl(),
						authorizedWebapi.getLimitRequest(),
						authorizedWebapi.getRemark() });
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI#updateAuthorizedWebapi(java.lang.String, int)
	 */
	@Override
	public int updateAuthorizedWebapi(Object[] params) {
		StringBuilder hql = new StringBuilder("update AuthorizedWebapi set ");
		if("limitRequest".equals(params[0])){
			Object[] temp = new Object[params.length-1];
			System.arraycopy(params, 1, temp, 0, params.length-1);
			params = temp;
			hql.append("limitRequest=? where id=?");
		}
		if("isauthorized".equals(params[0])){
			Object[] temp = new Object[params.length-1];
			System.arraycopy(params, 1, temp, 0, params.length-1);
			params = temp;
			hql.append("isauthorized=?, remark=? where id=?");
		}
		if("applyAgain".equals(params[0])){
			Object[] temp = new Object[params.length-1];
			System.arraycopy(params, 1, temp, 0, params.length-1);
			params = temp;
			hql = new StringBuilder("update AuthorizedWebapi aw set isauthorized=0, remark='' where aw.webapi.id=? and aw.user.id=?");
		}
		return basedao.executeUpdateWithHql(hql.toString(),params);
	}

}
