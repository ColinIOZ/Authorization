/***********************************************************************
 * FileName:  AuthorizedWebServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebService deal with webinfo business.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.big.authorization.dao.daoInterface.AuthorizedWebDaoI;
import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;


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
public class AuthorizedWebServiceImpl implements AuthorizedWebServiceI {
	private AuthorizedWebDaoI authorizedWebDao;

	public void setAuthorizedWebDao(AuthorizedWebDaoI authorizedWebDao) {
		this.authorizedWebDao = authorizedWebDao;
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebServiceI#authorizedWebCount(java.lang.Object[])
	 */
	@Override
	public int authorizedWebCount(Object[] params) {
		
		return authorizedWebDao.authorizedWebCount(params);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebServiceI#authorizedWebList(java.lang.Object[], int, int)
	 */
	@Override
	public List<AuthorizedWeb> authorizedWebList(Object[] params, int firstResult,int maxResults) {
		
		return authorizedWebDao.authorizedWebList(params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebServiceI#getAuthorizedWebByWebId(java.lang.String)
	 */
	@Override
	public AuthorizedWeb getAuthorizedWebByWebId(String webid, String userid) {
		
		return (AuthorizedWeb) authorizedWebDao.getAuthorizedWebByWebId(webid, userid);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebServiceI#applyForWebAuthorisation(com.big.authorization.po.AuthorizedWeb, java.lang.String, java.lang.String)
	 */
	@Override
	public int applyForWebAuthorisation(AuthorizedWeb authorizedWeb,
			String webid, String userid) {
		
		return authorizedWebDao.applyForWebAuthorisation(authorizedWeb, webid, userid);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebServiceI#updateAuthorizedWeb(java.lang.Object[])
	 */
	@Override
	public int updateAuthorizedWeb(Object[] params) {
		
		return authorizedWebDao.updateAuthorizedWeb(params);
	}

	
	

}
