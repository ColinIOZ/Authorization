/***********************************************************************
 * FileName:  WebInfoDaoI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebInfoDao interface which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoInterface;

import java.util.Collection;
import java.util.List;

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
public interface WebInfoDaoI{
	/**
	 * this method will invoke queryForListWithHqlByPage to get all the webinfo 
	 * @return : the query results
	 */
	public List webInfoList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke getCountWithHql to get the webinfo count
	 * @return : the query results
	 */
	public int webInfoCount(Object[] params);
	/**
	 * this method will invoke save method to insert this webinfo
	 * @param webinfo : the collection of input parameters
	 * @return : the result of operate
	 */
	public boolean addWebInfo(WebInfo webinfo);
	/**
	 * this method will invoke deleteById method in baseDao to delete this webinfo
	 * @param type : the entity type which will be delete
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebInfo(Class type, String id);
	/**
	 * this method will invoke deleteAll method in baseDao to delete webinfos
	 * @param collection : the collection of entity parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebInfos(Collection collection);
	/**
	 * this method will invoke getObjectById method in baseDao to get this webinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public Object getWebInfoById(Class type, String id);
	/**
	 * this method will invoke queryForObjectWithHql method in baseDao to get this webinfo
	 * @param webHost : the webHost parameter ;
	 * @return : the result of operate
	 */
	public Object getWebInfoByWebHost(String webHost);
	/**
	 * this method will invoke update method in WebInfoDao to update webinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean modifyWebInfo(WebInfo webinfo);
}
