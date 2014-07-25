/***********************************************************************
 * FileName:  WebapiStatisticsServiceI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiStatisticsService interface deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceInterface;

import java.util.List;
import java.util.Map;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiStatistics;


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
public interface WebapiStatisticsServiceI{
	/**
	 * this method will invoke getWebapiStatisticsByauthorizedWebapiId in WebapiStatisticsDao to get the WebapiStatistics
	 * @param authorizedWebapiId : the authorizedWebapiId parameter
	 * @return : the query results
	 */
	public WebapiStatistics getWebapiStatisticsByauthorizedWebapiId(String authorizedWebapiId);
	/**
	 * this method will invoke SaveWebapiStatistics in WebapiStatisticsDao to save the WebapiStatistics
	 * @param webapiStatistics : the webapiStatistics parameter
	 * @return : the query results
	 */
	public boolean saveWebapiStatistics(WebapiStatistics webapiStatistics);
	/**
	 * this method will invoke SaveWebapiStatistics in WebapiStatisticsDao to update the WebapiStatistics
	 * @param params : the parameters
	 * @return : the query results
	 */
	public boolean updateWebapiStatistics(Object[] params);
	/**
	 * this method will invoke getWebapiStatisticsList in WebapiStatisticsDao to get all the WebapiStatistics 
	 * @param params : the input parameters 
	 * @param firstResult : the start row number 
	 * @param maxResults : the rows witch is request to query
	 * @return : the query results
	 */
	public List<WebapiStatistics> getWebapiStatisticsList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke getWebapiStatisticsCount in WebapiStatisticsDao to get all the WebapiStatisticsCount 
	 * @param params : the input parameters 
	 * @return : the query results
	 */
	public int getWebapiStatisticsCount(Object[] params);
}
