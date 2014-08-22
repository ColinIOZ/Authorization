/***********************************************************************
 * FileName:  AuthorizedWebapiDaoI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebapiDao interface which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoInterface;

import java.util.List;

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
public interface AuthorizedWebapiDaoI{
	/**
	 * this method will invoke getCountWithHql in basedao to get all the authorizedWebapiCount 
	 * @param params : the input parameters 
	 * @return : the query results
	 */
	public int authorizedWebapiCount(Object[] params);
	/**
	 * this method will invoke queryForListWithHqlByPage in basedao to get all the authorizedWebapiList 
	 * @param params : the input parameters 
	 * @param firstResult : the start row number 
	 * @param maxResults : the rows witch is request to query
	 * @return : the query results
	 */
	public List authorizedWebapiList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke queryForObjectWithHql in basedao to get the authorizedWebapi 
	 * @param webid : the webapiId parameter
	 * @return : the query results
	 */
	public Object getAuthorizedWebByWebapiId(String webapiId, String userid);
	/**
	 * this method will invoke applyForWebapiAuthorisation in basedao to insert the authorizedWebapi 
	 * @param authorizedWebapi : the authorizedWebapi parameter
	 * @param webapiId : the webapiId parameter
	 * @param userid : the userid parameter
	 * @return : the operate results
	 */
	public int applyForWebapiAuthorisation(AuthorizedWebapi authorizedWebapi, String webapiId, String userid);
	/**
	 * this method will invoke updateAuthorizedWebapi in basedao to update the authorizedWebapi 
	 * @param params : the collection of parameters
	 * @return : the operate results
	 */
	public int updateAuthorizedWebapi(Object[] params);
	
}
