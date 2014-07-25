/***********************************************************************
 * FileName:  HibernateCallback.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f11
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This inteface is the hibernate callback method.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.hibernatedao;

import org.hibernate.Session;


/** CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * Project: authorization
 * Module ID:
 * Comments:
 * JDK version used: <JDK1.7>
 * Namespace: 
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified By：
 * Modified Date:
 * Why & What is modified:
 * Version: 0.1.0
 * 
 */

public interface HibernateCallback {
	/**
	 * the callback method of hibernate use for dynamic update
	 * @param session
	 * @return Object
	 * @throws Exception
	 */
	public Object doInHibernate(Session session)throws Exception;
}