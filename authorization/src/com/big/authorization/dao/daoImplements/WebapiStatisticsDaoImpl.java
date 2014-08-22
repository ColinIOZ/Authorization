/***********************************************************************
 * FileName:  WebapiStatisticsDaoImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiStatisticsDao class deal with webinfo business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;

import java.util.List;

import com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
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
public class WebapiStatisticsDaoImpl implements WebapiStatisticsDaoI{
	private HibernateBaseDao basedao;

	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI#getWebapiStatisticsByauthorizedWebapiId(java.lang.String)
	 */
	@Override
	public Object getWebapiStatisticsByauthorizedWebapiId(
			String authorizedWebapiId) {
		
		return basedao.queryForObjectWithHql(
				"from WebapiStatistics ws where ws.authorizedWebapi.id=?",
				new Object[] { authorizedWebapiId });
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI#SaveWebapiStatistics(com.big.authorization.po.WebapiStatistics)
	 */
	@Override
	public boolean saveWebapiStatistics(WebapiStatistics webapiStatistics) {
		
		return basedao.save(webapiStatistics);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI#updateWebapiStatistics(java.lang.String)
	 */
	@Override
	public int updateWebapiStatistics(Object[] params) {
		StringBuilder hql = new StringBuilder("update WebapiStatistics ws set ");
		if("newDay".equals(params[0])){
			Object[] temp = new Object[params.length-1];
			System.arraycopy(params, 1, temp, 0, params.length-1);
			params = temp;
			hql.append("requestDate=?, requestTimes=? where ws.authorizedWebapi.id=?");
		}else{
			hql.append("requestTimes=(requestTimes+1) where ws.authorizedWebapi.id=?");
		}
		return basedao.executeUpdateWithHql(hql.toString(), params);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI#getWebapiStatisticsList(java.lang.Object[], int, int)
	 */
	@Override
	public List getWebapiStatisticsList(Object[] params, int firstResult,
			int maxResults) {
		basedao.executeUpdateWithHql("update WebapiStatistics set requestTimes = requestTimes", null);
		return basedao
				.queryForListWithHqlByPage(
						"from WebapiStatistics ws inner join fetch ws.authorizedWebapi aw inner join fetch aw.webapi inner join fetch aw.user order by ws.requestDate",
						params, firstResult, maxResults);
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.WebapiStatisticsDaoI#getWebapiStatisticsCount(java.lang.Object[])
	 */
	@Override
	public int getWebapiStatisticsCount(Object[] params) {
		basedao.executeUpdateWithHql("update WebapiStatistics set requestTimes = requestTimes", null);
		return basedao
				.getCountWithHql(
						"from WebapiStatistics ws inner join fetch ws.authorizedWebapi aw inner join fetch aw.webapi inner join fetch aw.user",
						params);
	}
	
}
