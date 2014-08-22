/***********************************************************************
 * FileName:  WebInfoServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebInfoService deal with webinfo business.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.po.WebInfo;
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
public class WebInfoServiceImpl implements WebInfoServiceI{

	private WebInfoDaoI webinfodao;
	
	public void setWebinfodao(WebInfoDaoI webinfodao) {
		this.webinfodao = webinfodao;
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#webInfoList()
	 */
	@Override
	public List<WebInfo> webInfoList(Object[] params, int firstResult, int maxResults) {
		return webinfodao.webInfoList(params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#webInfoCount(java.lang.Object[])
	 */
	@Override
	public int webInfoCount(Object[] params) {
		return webinfodao.webInfoCount(params);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#addWebInfo(com.big.authorization.po.WebInfo)
	 */
	@Override
	public boolean addWebInfo(WebInfo webinfo) {

		return webinfodao.addWebInfo(webinfo);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#deleteWebInfo(java.lang.String)
	 */
	@Override
	public boolean deleteWebInfo(String id) {
		
		return webinfodao.deleteWebInfo(WebInfo.class, id);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#deleteWebInfos(java.lang.Class, java.lang.String[])
	 */
	@Override
	public boolean deleteWebInfos(String[] ids) {
		Collection webinfos = new ArrayList();
		WebInfo webinfo = new WebInfo();
		for(int i = 0; i < ids.length; i++){
			webinfo = (WebInfo) webinfodao.getWebInfoById(WebInfo.class, ids[i]);
			webinfos.add(webinfo);
		}
		return webinfodao.deleteWebInfos(webinfos);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#modifyWebInfo(java.lang.String)
	 */
	@Override
	public boolean modifyWebInfo(WebInfo webinfo) {
		
		return webinfodao.modifyWebInfo(webinfo);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#showWebInfoById(java.lang.String)
	 */
	@Override
	public WebInfo showWebInfoById(String id) {
		return (WebInfo) webinfodao.getWebInfoById(WebInfo.class, id);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebInfoServiceI#getWebInfoByWebHost(java.lang.String)
	 */
	@Override
	public WebInfo getWebInfoByWebHost(String webHost) {
		
		return (WebInfo) webinfodao.getWebInfoByWebHost(webHost);
	}
	
}
