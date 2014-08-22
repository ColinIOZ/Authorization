/***********************************************************************
 * FileName:  AuthorizedWebDaoImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebDao which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;

import java.util.List;


import com.big.authorization.dao.daoInterface.AuthorizedWebDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
import com.big.authorization.po.AuthorizedWeb;


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
public class AuthorizedWebDaoImpl implements AuthorizedWebDaoI {
	private HibernateBaseDao basedao;

	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}
	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebDaoI#authorizedWebCount(java.lang.Object[])
	 */
	@Override
	public int authorizedWebCount(Object[] params) {
		StringBuilder hql = new StringBuilder("from AuthorizedWeb aw inner join fetch aw.user inner join fetch aw.web ");
		if(params != null && "userid".equals(params[0])){
			params = new Object[]{params[1]};
			hql = hql.append("where aw.user.id=?");
		}
		if(params != null && "isauthorized".equals(params[0])){
			params = new Object[]{params[1]};
			hql = hql.append("where aw.isauthorized=?");
		}
		return basedao
				.getCountWithHql(hql.toString(),params);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebDaoI#authorizedWebList(java.lang.Object[], int, int)
	 */
	@Override
	public List authorizedWebList(Object[] params, int firstResult,
			int maxResults) {
		StringBuilder hql = new StringBuilder("from AuthorizedWeb aw inner join fetch aw.user inner join fetch aw.web ");
		if(params != null && "userid".equals(params[0])){
			params = new Object[]{params[1]};
			hql.append("where aw.user.id=? ");
		}
		if(params != null && "isauthorized".equals(params[0])){
			params = new Object[]{params[1]};
			hql = hql.append("where aw.isauthorized=?");
		}
		//hql.append("order by aw.isauthorized");
		return basedao
				.queryForListWithHqlByPage(hql.toString(), params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebDaoI#getAuthorizedWebByWebId(java.lang.String)
	 */
	@Override
	public Object getAuthorizedWebByWebId(String webid, String userid) {
		return basedao
				.queryForObjectWithHql(
						"from AuthorizedWeb aw inner join fetch aw.user inner join fetch aw.web where aw.web.id=? and aw.user.id=?",
						new Object[] { webid, userid});
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebDaoI#applyForWebAuthorisation(com.big.authorization.po.AuthorizedWeb, java.lang.String, java.lang.String)
	 */
	@Override
	public int applyForWebAuthorisation(AuthorizedWeb authorizedWeb,
			String webid, String userid) {
		StringBuilder sql = new StringBuilder("insert into authorized_web(id,user_id,web_id,isauthorized,remark) values(?,?,?,?,?)") ;
		return basedao.executeUpdateWithSql(
				sql.toString(),
				new Object[] { authorizedWeb.getId(), userid, webid,
						authorizedWeb.getIsauthorized(),
						authorizedWeb.getRemark() });
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.AuthorizedWebDaoI#updateAuthorizedWeb(java.lang.Object[])
	 */
	@Override
	public int updateAuthorizedWeb(Object[] params) {
		StringBuilder hql = new StringBuilder("update AuthorizedWeb set ");
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
			hql = new StringBuilder("update AuthorizedWeb aw set isauthorized=0, remark='' where aw.web.id=? and aw.user.id=?");
		}
		return basedao.executeUpdateWithHql(hql.toString(),params);
	}

}
