/***********************************************************************

 * FileName:  HibernateBaseDao.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f12
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is the hibernate template use for CURD.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.hibernatedao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.big.authorization.exception.DaoException;
import com.big.authorization.exception.UpdateException;
import com.big.authorization.po.User;


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

public class HibernateBaseDao{

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public HibernateBaseDao() {
		// TODO Auto-generated constructor stub
	}
	public HibernateBaseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * insert an entity to the authorization database 
	 * @param obj : the entity
	 * @return : the flag=true represent this method execute success
	 */
	public boolean save(Object obj){
		boolean flag = true;
		Session session = null;
		Transaction transaction = null;
		try {
			//session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			transaction.rollback();
			if(e instanceof StaleStateException)
				throw new UpdateException("inser error");
			else
				throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			//if(session != null)
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
			
		}
		return flag;
	}
	/**
	 * delete an entity from the authorization database 
	 * @param obj : the entity
	 * @return : the flag=true represent this method execute success
	 */
	public boolean delete(Object obj){
		boolean flag = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			transaction.rollback();
			if(e instanceof StaleStateException)
				throw new UpdateException("delete error");
			else
				throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return flag;
	}
	/**
	 * delete an entity from the authorization database via id
	 * @param type : the type of entity
	 * @param id : the id of entity
	 * @return : the flag=true represent this method execute success
	 */
	public boolean deleteById(Class type, Serializable id){
		 boolean flag = delete(getObjectById(type, id));
		 return flag;
	}
	/**
	 * delete all entities
	 * @param collection : the entities collection
	 * @return : the flag=true represent this method execute success
	 */
	public boolean deleteAll(Collection collection){
		boolean flag = true;
		for (Iterator iter = collection.iterator(); iter.hasNext(); flag = delete(iter.next()));
		return flag;
	}
	/**
	 * update an entity to the authorization database 
	 * @param obj : the entity
	 * @return : the flag=true represent this method execute success
	 */
	public boolean update(Object obj){
		boolean flag = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			transaction.rollback();
			if(e instanceof StaleStateException)
				throw new UpdateException("update error");
			else
				throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return flag;
	}
	
	/**
	 * query an entity from the authorization database via id
	 * @param type : the type of entity
	 * @param id : the id of entity
	 * @return : the entity after query
	 */
	public Object getObjectById(Class type, Serializable id){
		Object obj = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			obj = session.get(type, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return obj;
	}
	/**
	 * dynamic query or update database via the callback method
	 * @param hibernateCallback : the custom callback method
	 * @return : the entity after query
	 */
	public Object execute(HibernateCallback hibernateCallback){
		Object obj = new Object();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			obj = hibernateCallback.doInHibernate(session);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			if(e instanceof StaleStateException)
				throw new UpdateException("update or query error");
			else
				throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return obj;
	}
	/**
	 * set params for query statement
	 * @param query : org.hibernate.Query
	 * @param params : the request params
	 */
	public void initParam(Query query, Object[] params){
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
	}
	/**
	 * query object via hql statement
	 * @param hql : the hql statement
	 * @param params : the request params
	 * @return : the entity after query
	 */
	public Object queryForObjectWithHql(String hql, Object[] params){
		Object obj = new Object();
		Session session =  null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			initParam(query, params);
			obj = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return obj;
	}
	/**
	 * query object list via hql statement
	 * @param hql : the hql statement
	 * @param params : the request params
	 * @return : the entity after query
	 */
	public List queryForListWithHql(String hql, Object[] params){
		return queryForListWithHqlByPage(hql, params, 0, 0);
	}
	/**
	 * through hql paging statement query object list
	 * @param hql : the hql statement
	 * @param params : the request params
	 * @param firstResult : the start number of paging query
	 * @param maxResults : the count number og paging query
	 * @return : the entity after query
	 */
	public List queryForListWithHqlByPage(String hql, Object[] params, int firstResult, int maxResults){
		List list = new ArrayList();
		Session  session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			initParam(query, params);
			if(firstResult >= 0 && maxResults > 0){
				query.setFirstResult(firstResult).setMaxResults(maxResults);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return list;
	}
	/**
	 * dynamic query or update database via hql statement
	 * @param hql : the hql statement
	 * @param params : the request params
	 * @return : the updated rows
	 */
	public int executeUpdateWithHql(String hql, Object[] params){
		int row = 0;
		Session session = null;
		Transaction transaction = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery(hql);
			initParam(query, params);
			row = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return row;
	}
	/**
	 * get all the record which are corresponding the request params via hql statement
	 * @param hql : the hql statement
	 * @param params : the request params
	 * @return : the query rows
	 */
	public int getCountWithHql(String hql, Object[] params){
		int resultCount = queryForListWithHql(hql, params).size();
		return resultCount;
	}
	/**
	 * query object via sql statement
	 * @param sql : the sql statement
	 * @param params : the request params
	 * @return : the entity after query
	 */
	public Object queryForObjectWithSql(String sql, Object[] params){
		Object obj = new Object();
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sql);
			initParam(query, params);
			obj = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return obj;
	}
	/**
	 * query object list via sql statement
	 * @param sql : the sql statement
	 * @param params : the request params
	 * @return : the entity list after query
	 */
	public List queryForListWithSql(String sql, Object[] params){
		return queryForListWithSqlByPage(sql, params, 0, 0);
	}
	/**
	 * through sql paging statement query object list
	 * @param sql : the sql statement
	 * @param params : the request params
	 * @param firstResult : the start number of paging query
	 * @param maxResults : the count number og paging query
	 * @return : the entity list after query
	 */
	public List queryForListWithSqlByPage(String sql, Object[] params, int firstResult, int maxResults){
		List list = new ArrayList();
		Session  session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sql);
			initParam(query, params);
			if(firstResult >= 0 && maxResults > 0){
				query.setFirstResult(firstResult).setMaxResults(maxResults);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return list;
	}
	/**
	 * dynamic query or update database via sql statement
	 * @param sql : the sql statement
	 * @param params : the request params
	 * @return : the updated rows
	 */
	public int executeUpdateWithSql(String sql, Object[] params){
		int row = 0;
		Session session = null;
		Transaction transaction = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createSQLQuery(sql);
			initParam(query, params);
			row = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new DaoException("can't get connection or sql gramma error or close resource error");
		}finally{
			if(session != null)
				session.close();
			if(sessionFactory != null)
				sessionFactory.close();
		}
		return row; 
	}
	/**
	 * get all the record which are meet the request params via hql statement
	 * @param sql : the sql statement
	 * @param params : the request params
	 * @return : the query rows
	 */
	public int getCountWithSql(String sql, Object[] params){
		int resultCount  = queryForListWithHql(sql, params).size();
		return resultCount;
	}
}