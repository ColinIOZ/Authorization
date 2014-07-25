/***********************************************************************
 * FileName:  HibernateUtil.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f10
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is the hibernate util use for get connection.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.hibernatedao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sun.org.apache.regexp.internal.recompile;

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

public class HibernateUtil {
	/**
	 * the definition of SessionFactory
	 */
	private static SessionFactory sessionFactory;
	
	/**
	 * static initialization block is used for the assignment of SessionFactory
	 */
	static{
		try{
			Configuration config = new Configuration().configure();
			sessionFactory = config.buildSessionFactory();
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError();
		}
	}
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	public static void closeSession(Session session){
		if(session != null)
			session.close();
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void closeSessionFactory(){
		if(sessionFactory != null){
			sessionFactory.close();
		}
	}
}