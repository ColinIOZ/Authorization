package com.big.authorization.service.serviceInterface;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;

public interface ValidateLoginServiceI {

	public User getUser(String userName, String userPwd);

	public WebInfo getWeb(String webHost);

	public AuthorizedWeb getAuthorizedWeb(WebInfo webinfo, User user);

}
