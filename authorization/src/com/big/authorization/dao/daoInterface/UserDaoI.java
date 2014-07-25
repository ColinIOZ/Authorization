/***********************************************************************
 * FileName:  UserDaoI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is UserDao interface which is interact with database .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.dao.daoInterface;

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
public interface UserDaoI{
	/**
	 * this method will invoke queryForObjectWithHql method in HibernateBaseDao to check user login on the basis of input username and password
	 * @param username : the input username
	 * @param userpwd : the input password
	 * @return : after match database return user info 
	 */
	public User userLoginByName(String username, String userpwd);
	/**
	 * this method will invoke queryForObjectWithHql method in HibernateBaseDao to check user login on the basis of input email and password
	 * @param username : the input username
	 * @param userpwd : the input password
	 * @return : after match database return user info 
	 */
	public User userLoginByEmail(String email, String userpwd);
	/**
	 * this method will invoke save method in HibernateBaseDao to complete user register
	 * @param user : the user entity include all register information of user input
	 * @return : the result is true represent this method execute success
	 */
	public boolean userRegister(User user);
	/**
	 * this method will judge whether the email exists in the database
	 * @param email : the inpur email
	 * @return : the validate result
	 */
	public User validateEmail(String email);
	/**
	 * this method will judge whether the user name exists in the database
	 * @param username : the input user name
	 * @return : the validate result
	 */
	public User validateUserName(String username);
	/**
	 * this method will judge whether the user pwd exists in the database
	 * @param userpwd : the input user pwd
	 * @return : the validate result
	 */
	public User validateUserPwd(String userid, String userpwd);
	/**
	 * this method will complete user info 
	 * @param user : the object which will be updated
	 * @return : the validate result
	 */
	public boolean completeUserInfo(User user);
	/**
	 * this method will complete the modify for user pwd
	 * @param user : the object for update
	 * @return
	 */
	public boolean modifyUserPwd(User user);
	/**
	 * this method will update user
	 * @param params : the params
	 * @return
	 */
	public boolean updateUser(User user);
}
