/***********************************************************************
 * FileName:  AuthorizedWebapiServiceI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebapiService interface deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceInterface;

import java.util.List;
import java.util.Map;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;


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
public interface AuthorizedWebapiServiceI{
	/**
	 * this method will invoke authorizedWebapiCount in AuthorizedWebapiDao to get all the authorizedWebapiCount 
	 * @param params : the input parameters 
	 * @return : the query results
	 */
	public int authorizedWebapiCount(Object[] params);
	/**
	 * this method will invoke authorizedWebapiList in AuthorizedWebapiDao to get all the authorizedWebapiList 
	 * @param params : the input parameters 
	 * @param firstResult : the start row number 
	 * @param maxResults : the rows witch is request to query
	 * @return : the query results
	 */
	public List<AuthorizedWebapi> authorizedWebapiList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke getAuthorizedWebByWebapiId in AuthorizedWebapiDao to get the authorizedWebapi 
	 * @param webid : the webapiId parameter
	 * @return : the query results
	 */
	public AuthorizedWebapi getAuthorizedWebByWebapiId(String webapiId, String userid);
	/**
	 * this method will invoke applyForWebapiAuthorisation in AuthorizedWebapiDao to insert the authorizedWebapi 
	 * @param authorizedWebapi : the authorizedWebapi parameter
	 * @param webspiId : the webspiId parameter
	 * @param userid : the userid parameter
	 * @return : the query results
	 */
	public int applyForWebapiAuthorisation(AuthorizedWebapi authorizedWebapi, String webapiId, String userid);
	/**
	 * this method will invoke updateAuthorizedWebapi in AuthorizedWebapiDao to update the authorizedWebapi 
	 * @param params : the collection of parameters
	 * @return : the operate results
	 */
	public int updateAuthorizedWebapi(Object[] params);

}
