/***********************************************************************
 * FileName:  WebapiInfoServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiInfoServiceImpl deal with webinfo business.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.big.authorization.dao.daoInterface.WebapiInfoDaoI;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiInfo;
import com.big.authorization.service.serviceInterface.WebapiInfoServiceI;


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
public class WebapiInfoServiceImpl implements WebapiInfoServiceI{

	private WebapiInfoDaoI webapiInfoDao;
	public void setWebapiInfoDao(WebapiInfoDaoI webapiInfoDao) {
		this.webapiInfoDao = webapiInfoDao;
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#webInfoList()
	 */
	@Override
	public List<WebapiInfo> webapiInfoList(Object[] params, int firstResult, int maxResults) {
		return webapiInfoDao.webapiInfoList(params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#webInfoCount(java.lang.Object[])
	 */
	@Override
	public int webapiInfoCount(Object[] params) {
		return webapiInfoDao.webapiInfoCount(params);
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiInfoServiceI#addWebapiInfo(com.big.authorization.po.WebapiInfo)
	 */
	@Override
	public boolean addWebapiInfo(WebapiInfo webapiinfo) {
		return webapiInfoDao.addWebapiInfo(webapiinfo);
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiInfoServiceI#deleteWebapiInfo(java.lang.Class, java.lang.String)
	 */
	@Override
	public boolean deleteWebapiInfo(String id) {
		
		return webapiInfoDao.deleteWebapiInfo(WebapiInfo.class, id);
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiInfoServiceI#deleteWebapiInfos(java.lang.String[])
	 */
	@Override
	public boolean deleteWebapiInfos(String[] ids) {
		Collection webapiinfos = new ArrayList();
		WebapiInfo webapiinfo = new WebapiInfo();
		for(int i = 0; i < ids.length; i++){
			webapiinfo = (WebapiInfo) webapiInfoDao.getWebapiInfoById(WebapiInfo.class, ids[i]);
			webapiinfos.add(webapiinfo);
		}
		return webapiInfoDao.deleteWebapiInfos(webapiinfos);
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiInfoServiceI#modifyWebapiInfo(com.big.authorization.po.WebapiInfo)
	 */
	@Override
	public boolean modifyWebapiInfo(WebapiInfo webapiInfo) {
		return webapiInfoDao.modifyWebapiInfo(webapiInfo);
	}
	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiInfoServiceI#showWebapiInfoById(java.lang.String)
	 */
	@Override
	public WebapiInfo showWebapiInfoById(String id) {
		
		return (WebapiInfo) webapiInfoDao.getWebapiInfoById(WebapiInfo.class, id);
	}
	
}
