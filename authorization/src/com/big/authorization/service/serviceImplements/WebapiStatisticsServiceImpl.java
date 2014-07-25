/***********************************************************************
 * FileName:  WebapiStatisticsServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiStatisticsService class deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import java.util.List;
import java.util.Map;

import com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiStatistics;
import com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI;


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
public class WebapiStatisticsServiceImpl implements WebapiStatisticsServiceI{
	private WebapiStatisticsDaoI webapiStatisticsDao;

	public void setWebapiStatisticsDao(WebapiStatisticsDaoI webapiStatisticsDao) {
		this.webapiStatisticsDao = webapiStatisticsDao;
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI#getWebapiStatisticsByauthorizedWebapiId(java.lang.String)
	 */
	@Override
	public WebapiStatistics getWebapiStatisticsByauthorizedWebapiId(
			String authorizedWebapiId) {
		
		return (WebapiStatistics) webapiStatisticsDao.getWebapiStatisticsByauthorizedWebapiId(authorizedWebapiId);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI#SaveWebapiStatistics(com.big.authorization.po.WebapiStatistics)
	 */
	@Override
	public boolean saveWebapiStatistics(WebapiStatistics webapiStatistics) {
		
		return webapiStatisticsDao.saveWebapiStatistics(webapiStatistics);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI#updateWebapiStatistics(java.lang.String)
	 */
	@Override
	public boolean updateWebapiStatistics(Object[] params) {
		if(webapiStatisticsDao.updateWebapiStatistics(params) != 0)
			return true;
		else
			return false;
		
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI#getWebapiStatisticsList(java.lang.Object[], int, int)
	 */
	@Override
	public List<WebapiStatistics> getWebapiStatisticsList(Object[] params, int firstResult,
			int maxResults) {
		
		return webapiStatisticsDao.getWebapiStatisticsList(params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.WebapiStatisticsServiceI#getWebapiStatisticsCount(java.lang.Object[])
	 */
	@Override
	public int getWebapiStatisticsCount(Object[] params) {
		
		return webapiStatisticsDao.getWebapiStatisticsCount(params);
	}
	
}
