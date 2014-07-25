/***********************************************************************
 * FileName:  UserServiceI.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is UserService interface deal with user business .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceInterface;

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
public interface UserServiceI{
	
	/**
	 * invoke userLogin method in UserDao to check user login on the basis of the input username and password
	 * @param username : the input username
	 * @param userpwd : the input password
	 * @return : after match database return user info 
	 */
	public User userLogin(String username, String userpwd);
	/**
	 * invoke userRegister method in UserDao to complete user register
	 * @param user : the entity of user information
	 * @return : the result of this method
	 */
	public boolean userRegister(User user);
	/**
	 * this method will judge whether the email exists in the database
	 * @param email : the input email
	 * @return
	 */
	public User validateEmail(String email);
	/**
	 * this method will judge whether the user name exists in the database
	 * @param username : the input user name
	 * @return
	 */
	public User validateUserName(String username);
	/**
	 * this method will judge whether the user pwd exists in the database
	 * @param userpwd : the input user pwd
	 * @return
	 */
	public User validateUserPwd(String userid, String userpwd);
	/**
	 * this method will complete the user Info
	 * @param user : the object for update
	 * @return
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
