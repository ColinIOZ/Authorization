/***********************************************************************
 * FileName:  UserDaoI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is UserDao which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoImplements;


import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
import com.big.authorization.po.User;


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
public class UserDaoImpl implements UserDaoI{

	private HibernateBaseDao basedao;
	
	public void setBasedao(HibernateBaseDao basedao) {
		this.basedao = basedao;
	}
	
	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#userLoginByName(java.lang.String, java.lang.String)
	 */
	@Override
	public User userLoginByName(String username, String userpwd) {
		return (User) basedao.queryForObjectWithHql(
				"from User where userName = ? and userPwd = ?", new Object[] {
						username, userpwd });

	}
	
	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#userLoginByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public User userLoginByEmail(String email, String userpwd) {
		
		return (User) basedao.queryForObjectWithHql(
				"from User where email = ? and userPwd = ?", new Object[] {
						email, userpwd });
		
	}

	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#userRegister(com.big.authorization.po.User)
	 */
	@Override
	public boolean userRegister(User user) {
		return basedao.save(user);
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#validateEmail(java.lang.String)
	 */
	@Override
	public User validateEmail(String email) {
		
		return (User) basedao.queryForObjectWithHql("from User where email = ?", new Object[]{email});
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#validateUserName(java.lang.String)
	 */
	@Override
	public User validateUserName(String username) {
		// TODO Auto-generated method stub
		return (User) basedao.queryForObjectWithHql("from User where user_name = ?", new Object[]{username});
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#completeUserInfo(com.big.authorization.po.User)
	 */
	@Override
	public boolean completeUserInfo(User user) {
		int row = basedao
				.executeUpdateWithHql(
						"update User set institute = ?,homepage = ?,selfIntroduction = ? where id = ?",
						new Object[] { user.getInstitute(), user.getHomepage(),
								user.getSelfIntroduction(), user.getId() });
		if(row != 0)
			return true;
		else 
			return false;
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#validateUserPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public User validateUserPwd(String userid, String userpwd) {
		
		return (User) basedao.queryForObjectWithHql("from User where id = ? and user_pwd = ?", new Object[]{userid,userpwd});
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#modifyUserPwd(com.big.authorization.po.User)
	 */
	@Override
	public boolean modifyUserPwd(User user) {
		int row = basedao.executeUpdateWithHql(
				"update User set userPwd = ? where id = ?", new Object[] {
						user.getUserPwd(), user.getId() });
		if(row != 0)
			return true;
		else 
			return false;
	}


	/**
	 * @see com.big.authorization.dao.daoInterface.UserDaoI#updateUser(java.lang.Object[])
	 */
	@Override
	public boolean updateUser(User user) {
		int row = basedao.executeUpdateWithHql(
				"update User set userPwd = ? where email = ?", new Object[] {
						user.getUserPwd(), user.getEmail() });
		if(row != 0)
			return true;
		else 
			return false;
	}
}
