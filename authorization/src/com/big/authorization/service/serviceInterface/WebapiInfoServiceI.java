/***********************************************************************
 * FileName:  WebapiInfoServiceI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiInfoService interface deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceInterface;

import java.util.List;
import java.util.Map;

import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiInfo;


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
public interface WebapiInfoServiceI{
	/**
	 * this method will invoke webapiInfoList in WebapiInfoDao to get all the webapiinfo 
	 * @param params : the input parameters 
	 * @param firstResult : the start row number 
	 * @param maxResults : the rows witch is request to query
	 * @return : the query results
	 */
	public List<WebapiInfo> webapiInfoList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke webapiInfoCount in WebapiInfoDao to get all the webapiinfoCount 
	 * @param params: the query results
	 * @return : the query results
	 */
	public int webapiInfoCount(Object[] params);
	/**
	 * this method will invoke addWebapiInfo method in WebapiInfoDao to insert this webapiinfo
	 * @param webinfo : the collection of input parameters
	 * @return : the result of operate
	 */
	public boolean addWebapiInfo(WebapiInfo webapiinfo);
	/**
	 * this method will invoke deleteWebapiInfo method in WebapiInfoDao to delete this webapiinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebapiInfo(String id);
	/**
	 * this method will invoke deleteWebapiInfos method in WebapiInfoDao to delete webapiinfos
	 * @param id : the ids parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebapiInfos(String[] ids);
	/**
	 * this method will invoke modifyWebapiInfo method in WebapiInfoDao to update webapiinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean modifyWebapiInfo(WebapiInfo webapiInfo);
	/**
	 * this method will invoke getWebapiInfoById method in WebapiInfoDao to get webapiinfo
	 * @param id : the id parameter
	 * @return : the result of operate
	 */
	public WebapiInfo showWebapiInfoById(String id);

}
