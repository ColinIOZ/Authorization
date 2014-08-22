/***********************************************************************
 * FileName:  WebapiInfoDaoImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiInfoDaoImpl which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;

import java.util.Collection;
import java.util.List;

import com.big.authorization.dao.daoInterface.WebapiInfoDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
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
/**
 * @author huan
 *
 */
public class WebapiInfoDaoImpl implements WebapiInfoDaoI{

	private HibernateBaseDao basedao;
	
	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#webInfoList()
	 */
	@Override
	public List webapiInfoList(Object[] params, int firstResult, int maxResults) {
		StringBuilder hql = new StringBuilder("from WebapiInfo ");
		if(params != null && "apiName".equals(params[0])){
			params = new Object[]{"%"+params[1]+"%"};
			hql = hql.append("where apiName like ? ");
		}
		hql.append("order by addTime desc");
		return basedao.queryForListWithHqlByPage(hql.toString(), params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#webInfoCount()
	 */
	@Override
	public int webapiInfoCount(Object[] params) {
		StringBuilder hql = new StringBuilder("from WebapiInfo ");
		if(params != null && "apiName".equals(params[0])){
			params = new Object[]{"%"+params[1]+"%"};
			hql = hql.append("where apiName like ? ");
		}
		return basedao.getCountWithHql(hql.toString(), params);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#addWebapiInfo(com.big.authorization.po.WebInfo)
	 */
	@Override
	public boolean addWebapiInfo(WebapiInfo webapiinfo) {
		return basedao.save(webapiinfo);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#deleteWebapiInfo(java.lang.Class, java.lang.String)
	 */
	@Override
	public boolean deleteWebapiInfo(Class type, String id) {
		
		return basedao.deleteById(type, id);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#deleteWebapiInfos(java.util.Collection)
	 */
	@Override
	public boolean deleteWebapiInfos(Collection collection) {
		
		return basedao.deleteAll(collection);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#getWebapiInfoById(java.lang.String)
	 */
	@Override
	public Object getWebapiInfoById(Class type, String id) {
		return basedao.getObjectById(type, id);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiInfoDaoI#modifyWebapiInfo(com.big.authorization.po.WebapiInfo)
	 */
	@Override
	public boolean modifyWebapiInfo(WebapiInfo webapiInfo) {
		int row = basedao
				.executeUpdateWithHql(
						"update WebapiInfo set api_name=?,api_url=?,api_usage=?,add_time=? where id=?",
						new Object[] { webapiInfo.getApiName(),
								webapiInfo.getApiUrl(),
								webapiInfo.getApiUsage(),
								webapiInfo.getAddTime(), webapiInfo.getId() });
		if (row != 0)
			return true;
		else
			return false;
	}

}
