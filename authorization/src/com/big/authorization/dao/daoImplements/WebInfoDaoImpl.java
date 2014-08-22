/***********************************************************************
 * FileName:  WebInfoDaoImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebInfoDao which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;

import java.util.Collection;
import java.util.List;

import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
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
/**
 * @author huan
 *
 */
public class WebInfoDaoImpl implements WebInfoDaoI{

	private HibernateBaseDao basedao;
	
	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}
	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#webInfoList()
	 */
	@Override
	public List webInfoList(Object[] params, int firstResult, int maxResults) {
		StringBuilder hql = new StringBuilder("from WebInfo ");
		if(params != null && "webName".equals(params[0])){
			params = new Object[]{"%"+params[1]+"%"};
			hql = hql.append("where webName like ? ");
		}
		hql.append("order by addTime desc");
		return basedao.queryForListWithHqlByPage(hql.toString(), params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#webInfoCount()
	 */
	@Override
	public int webInfoCount(Object[] params) {
		StringBuilder hql = new StringBuilder("from WebInfo ");
		if(params != null && "webName".equals(params[0])){
			params = new Object[]{"%"+params[1]+"%"};
			hql = hql.append("where webName like ? ");
		}
		return basedao.getCountWithHql(hql.toString(), params);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#addWebInfo(com.big.authorization.po.WebInfo)
	 */
	@Override
	public boolean addWebInfo(WebInfo webinfo) {
		return basedao.save(webinfo);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#deleteWebInfo(java.lang.Class, java.lang.String)
	 */
	@Override
	public boolean deleteWebInfo(Class type, String id) {
		
		return basedao.deleteById(type, id);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#getWebInfoById(java.lang.String)
	 */
	@Override
	public Object getWebInfoById(Class type, String id) {
		
		return basedao.getObjectById(type, id);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#deleteWebInfos(java.util.Collection)
	 */
	@Override
	public boolean deleteWebInfos(Collection collection) {
		return basedao.deleteAll(collection);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#modifyWebInfo(java.lang.String)
	 */
	@Override
	public boolean modifyWebInfo(WebInfo webinfo) {
		int row = basedao
				.executeUpdateWithHql(
						"update WebInfo set web_name=?,web_host=?,logo_url=?,brief_introduction=?,add_time=? where id=?",
						new Object[] { webinfo.getWebName(),
								webinfo.getWebHost(), webinfo.getLogoUrl(),
								webinfo.getBriefIntroduction(),
								webinfo.getAddTime(), webinfo.getId() });
		if (row != 0)
			return true;
		else
			return false;
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebInfoDaoI#getWebInfoByWebHost(java.lang.Class, java.lang.String)
	 */
	@Override
	public Object getWebInfoByWebHost(String webHost) {
		
		return basedao.queryForObjectWithHql("from WebInfo where webHost = ?", new Object[]{webHost});
	}

}
