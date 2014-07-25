package com.big.authorization.server;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.big.authorization.dao.daoImplements.AuthorizedWebDaoImpl;
import com.big.authorization.dao.daoImplements.UserDaoImpl;
import com.big.authorization.dao.daoImplements.WebInfoDaoImpl;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
import com.big.authorization.dao.hibernatedao.HibernateUtil;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceImplements.AuthorizedWebServiceImpl;
import com.big.authorization.service.serviceImplements.UserServiceImpl;
import com.big.authorization.service.serviceImplements.WebInfoServiceImpl;
import com.big.authorization.util.MD5Util;

public class ValidateLoginWS {
	
	private HibernateBaseDao basedao = new HibernateBaseDao();
	private String userName;
	private String userPwd;
	private String webHost;
	private User user;
	private WebInfo webinfo;
	private AuthorizedWeb authorizedWeb;
	private String validateLoginWSResult = "";
		
	private User getUser(String userName, String userPwd){
		user = new User();
		UserDaoImpl userdao = new UserDaoImpl();
		userdao.setBasedao(basedao);
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserdao(userdao);
		user = userService.userLogin(userName, MD5Util.MD5(userPwd));
			
		return user;
	}
	private WebInfo getWeb(String webHost){
	    webinfo = new WebInfo();
		WebInfoDaoImpl webinfodao = new WebInfoDaoImpl();
		webinfodao.setBasedao(basedao);
		WebInfoServiceImpl WebInfoService = new WebInfoServiceImpl();
		WebInfoService.setWebinfodao(webinfodao);
		webinfo = (WebInfo) WebInfoService.getWebInfoByWebHost(webHost);
			
		return webinfo;
	}
	private AuthorizedWeb getAuthorizedWeb(WebInfo webinfo, User user){
	    authorizedWeb = new AuthorizedWeb();
		AuthorizedWebDaoImpl authorizedWebDao = new AuthorizedWebDaoImpl();
		authorizedWebDao.setBasedao(basedao);
		AuthorizedWebServiceImpl authorizedWebService = new AuthorizedWebServiceImpl();
		authorizedWebService.setAuthorizedWebDao(authorizedWebDao);
		authorizedWeb = (AuthorizedWeb) authorizedWebService
				.getAuthorizedWebByWebId(webinfo.getId(),user.getId());
		return authorizedWeb;
	}
	public String getValidateLoginWSResult(String userName, String userPwd, String webHost){
		if((userName != null || !"".equals(userName)) 
				&& (userPwd != null || !"".equals(userPwd))
				&& (webHost != null || !"".equals(webHost))){
			if(getUser(userName,userPwd) != null 
					&& getWeb(webHost) != null 
					&& getAuthorizedWeb(webinfo, user) != null){
				if(user.getRole() == 0){
					if(authorizedWeb.getIsauthorized() == 1)
						validateLoginWSResult = "Authorized user";
					else
						validateLoginWSResult = "Not authorize user";
				}else{
					validateLoginWSResult = "Admin";
				}
				
			}else{
				validateLoginWSResult = "Error message";
			}
			
		}else{
			validateLoginWSResult = "Error message";
		}
		return validateLoginWSResult;
	}
	public static void main(String[] args) {
		ValidateLoginWS test = new ValidateLoginWS();
		System.out.println(test.getValidateLoginWSResult("999", "999999", "nvnvn"));
	}
}
