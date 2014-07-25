/***********************************************************************
 * FileName:  AuthorizedWebapiServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebapiService deal with webinfo business.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.big.authorization.dao.daoInterface.AuthorizedWebDaoI;
import com.big.authorization.dao.daoInterface.AuthorizedWebapiDaoI;
import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI;
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
public class AuthorizedWebapiServiceImpl implements AuthorizedWebapiServiceI {
	private AuthorizedWebapiDaoI authorizedWebapiDao;

	public void setAuthorizedWebapiDao(AuthorizedWebapiDaoI authorizedWebapiDao) {
		this.authorizedWebapiDao = authorizedWebapiDao;
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI#authorizedWebapiCount(java.lang.Object[])
	 */
	@Override
	public int authorizedWebapiCount(Object[] params) {
		
		return authorizedWebapiDao.authorizedWebapiCount(params);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI#authorizedWebapiList(java.lang.Object[], int, int)
	 */
	@Override
	public List<AuthorizedWebapi> authorizedWebapiList(Object[] params, int firstResult,int maxResults) {
		
		return authorizedWebapiDao.authorizedWebapiList(params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI#getAuthorizedWebByWebapiId(java.lang.String)
	 */
	@Override
	public AuthorizedWebapi getAuthorizedWebByWebapiId(String webapiId, String userid) {
		
		return (AuthorizedWebapi) authorizedWebapiDao.getAuthorizedWebByWebapiId(webapiId, userid);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI#applyForWebapiAuthorisation(com.big.authorization.po.AuthorizedWeb, java.lang.String, java.lang.String)
	 */
	@Override
	public int applyForWebapiAuthorisation(AuthorizedWebapi authorizedWebapi,
			String webapiId, String userid) {
		
		return authorizedWebapiDao.applyForWebapiAuthorisation(authorizedWebapi, webapiId, userid);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI#updateAuthorizedWebapi(java.lang.String, int)
	 */
	@Override
	public int updateAuthorizedWebapi(Object[] params) {
		
		return authorizedWebapiDao.updateAuthorizedWebapi(params);
	}

	
	

}
