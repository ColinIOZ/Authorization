/***********************************************************************
 * FileName:  WebapiInfoDaoI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiInfoDaoI interface which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoInterface;

import java.util.Collection;
import java.util.List;

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
public interface WebapiInfoDaoI{
	/**
	 * this method will invoke queryForListWithHqlByPage to get all the webapiinfo 
	 * @return : the query results
	 */
	public List webapiInfoList(Object[] params, int firstResult, int maxResults);
	/**
	 * this method will invoke getCountWithHql to get the webapiinfo count
	 * @return : the query results
	 */
	public int webapiInfoCount(Object[] params);
	/**
	 * this method will invoke save method to insert this webapiinfo
	 * @param webinfo : the collection of input parameters
	 * @return : the result of operate
	 */
	public boolean addWebapiInfo(WebapiInfo webapiinfo);
	/**
	 * this method will invoke deleteById method in baseDao to delete this webapiinfo
	 * @param type : the entity type which will be delete
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebapiInfo(Class type, String id);
	/**
	 * this method will invoke deleteAll method in baseDao to delete webapiinfos
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean deleteWebapiInfos(Collection collection);
	/**
	 * this method will invoke getObjectById method in baseDao to get webapiinfo
	 *@param type : the entity type which will be get
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public Object getWebapiInfoById(Class type, String id);
	/**
	 * this method will invoke update method in WebapiInfoDao to update webapiinfo
	 * @param id : the id parameter ;
	 * @return : the result of operate
	 */
	public boolean modifyWebapiInfo(WebapiInfo webapiinfo);
}
