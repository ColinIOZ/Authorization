/***********************************************************************
 * FileName:  UserServiceImpl.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is UserService deal with user business.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.service.serviceImplements;

import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.po.User;
import com.big.authorization.service.serviceInterface.UserServiceI;


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
public class UserServiceImpl implements UserServiceI{
	
	private UserDaoI userdao;
	
	public void setUserdao(UserDaoI userdao) {
		this.userdao = userdao;
	}

	/**
	 * check user login on the basis of the input username and password
	 * @see com.big.authorization.service.serviceInterface.UserSrviceI#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User userLogin(String username, String userpwd) {
		if(username.indexOf('@') == -1)
			return userdao.userLoginByName(username, userpwd);
		else 
			return userdao.userLoginByEmail(username, userpwd);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#userRegister(com.big.authorization.po.User)
	 */
	@Override
	public boolean userRegister(User user) {
		if(user != null)
			return userdao.userRegister(user);
		else
			return false;
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#validateEmail(java.lang.String)
	 */
	@Override
	public User validateEmail(String email) {
		return userdao.validateEmail(email);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#validateUserName(java.lang.String)
	 */
	@Override
	public User validateUserName(String username) {
		return userdao.validateUserName(username);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#completeUserInfo(com.big.authorization.po.User)
	 */
	@Override
	public boolean completeUserInfo(User user) {
		return userdao.completeUserInfo(user);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#validateUserPwd(java.lang.String)
	 */
	@Override
	public User validateUserPwd(String userid, String userpwd) {
		
		return userdao.validateUserPwd(userid, userpwd);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#modifyUserPwd(com.big.authorization.po.User)
	 */
	@Override
	public boolean modifyUserPwd(User user) {
		
		return userdao.modifyUserPwd(user);
	}

	/**
	 * @see com.big.authorization.service.serviceInterface.UserServiceI#updateUser(java.lang.Object[])
	 */
	@Override
	public boolean updateUser(User user) {
		
		return userdao.updateUser(user);
	}
	
	
}
