package com.big.authorization.service.serviceImplements;

import com.big.authorization.dao.daoInterface.AuthorizedWebDaoI;
import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.ValidateLoginServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.util.MD5Util;

public class ValidateLoginServiceImpl implements ValidateLoginServiceI{
	private UserDaoI userdao;
	private WebInfoDaoI webinfodao;
	private AuthorizedWebDaoI authorizedWebDao;
	
	public User getUser(String userName, String userPwd){
		if(userName.indexOf('@') == -1)
			return userdao.userLoginByName(userName, MD5Util.MD5(userPwd));
		else 
			return userdao.userLoginByEmail(userName, MD5Util.MD5(userPwd));
	}
	public WebInfo getWeb(String webHost){
		return (WebInfo) webinfodao.getWebInfoByWebHost(webHost);
	}
	public AuthorizedWeb getAuthorizedWeb(WebInfo webinfo, User user){
		return (AuthorizedWeb) authorizedWebDao
			.getAuthorizedWebByWebId(webinfo.getId(),user.getId());
	}

}
