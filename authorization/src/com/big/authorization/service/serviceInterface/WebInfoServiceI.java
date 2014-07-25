/***********************************************************************
 * FileName:  WebInfoServiceI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebInfoService interface deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceInterface;

import java.util.List;
import java.util.Map;

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
public interface WebInfoServiceI{
	/**
	 * this method will invoke webInfoList in WebInfoDao to get all the webinfo 
	 * @param params : the input parameters 
	 * @param firstResult : the start row number 
	 * @param maxResults : the rows witch is request to query
	 * @return : the query results
	 */
	public List<WebInfo> webInfoList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke webInfoCount in WebInfoDao to get all the webinfoCount 
	 * @param params : the input parameters 
	 * @return : the query results
	 */
	public int webInfoCount(Object[] params);
	/**
	 * this method will invoke addWebInfo method in WebInfoDao to insert this webinfo
	 * @param webinfo : the collection of input parameters
	 * @return : the result of operate
	 */
	public boolean addWebInfo(WebInfo webinfo);
	/**
	 * this method will invoke deleteWebInfo method in WebInfoDao to delete this webinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebInfo(String id);
	/**
	 * this method will invoke deleteWebInfos method in WebInfoDao to delete webinfos
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebInfos(String[] ids);
	/**
	 * this method will invoke modifyWebInfo method in WebInfoDao to update webinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean modifyWebInfo(WebInfo webinfo);
	/**
	 * this method will invoke getWebInfoById method in WebInfoDao to get webinfo
	 * @param id : the id parameter
	 * @return : the result of operate
	 */
	public WebInfo showWebInfoById(String id);
	/**
	 * this method will invoke queryForObjectWithHql method in WebInfoDao to get this webinfo
	 * @param webHost : the webHost parameter ;
	 * @return : the result of operate
	 */
	public WebInfo getWebInfoByWebHost(String webHost);
}
