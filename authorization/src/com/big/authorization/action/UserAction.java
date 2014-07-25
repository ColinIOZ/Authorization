/***********************************************************************
 * FileName:  UserAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is UserAction deal with front page displays  .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;
import org.apache.struts2.ServletActionContext;

import com.big.authorization.po.User;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.service.serviceInterface.WebapiInfoServiceI;
import com.big.authorization.util.Constants;
import com.big.authorization.util.IDUtil;
import com.big.authorization.util.MD5Util;
import com.big.authorization.util.PageUtil;
import com.big.authorization.util.PrintWriterUtil;
import com.big.authorization.util.RandomNumUtil;
import com.big.authorization.util.SendEmailUtil;
import com.big.authorization.util.WebUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


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
public class UserAction extends ActionSupport{
	private UserServiceI userService;
	private String id;
	private String username;
	private String userpwd;
	private String email;
	private String validateCode;
	private String institute;
	private String homepage;
	private String selfIntroduction;
	private String newpwd;
	private String num;
	
	public UserServiceI getUserService() {
		return userService;
	}
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * invoke userLogin method in UserService to sure which page to forward after login
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String userLogin() throws Exception {
		
		String sessionCode = (String) ActionContext.getContext().getSession()
				.get("sessionCode");
		if(!"".equals(username) && !"".equals(userpwd) && !"".equals(validateCode)){
			if (sessionCode != null && sessionCode.equalsIgnoreCase(validateCode)) {
				User user = new User();
				user = userService.userLogin(username, MD5Util.MD5(userpwd));
				if (user != null && user.getRole() == 0) {
					ActionContext.getContext().getSession().put("user", user);
					return "userlogin";
				} else if (user != null && user.getRole() == 1) {
					User admin = user;
					ActionContext.getContext().getSession().put("admin", admin);
					return "adminlogin";
				} else {
					ActionContext.getContext().put("loginName",username);
					ActionContext.getContext().put("loginPwd",userpwd);
					ActionContext.getContext().put("loginError",this.getText("loginError"));
					return "error";
				}
			}else{
				ActionContext.getContext().put("loginName",username);
				ActionContext.getContext().put("loginPwd",userpwd);
				ActionContext.getContext().put("loginError",this.getText("validateCodeError"));
				return "error";
			}

		}else{
			ActionContext.getContext().put("loginError", this.getText("null_tip"));
			return "error";
		}
		
	}
	/**
	 * invoke userRegister in UserService to complete user register
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String userRegister() throws Exception {
		
		String path = "";
		String sessionCode = (String) ActionContext.getContext().getSession().get("sessionCode");
		User regUser = new User();
		regUser.setEmail(email);
		regUser.setUserName(username);
		if(userService.validateEmail(email) != null){
			ActionContext.getContext().put("email_occupy", this.getText("email_occupy"));
			ActionContext.getContext().put("regUser", regUser);
			path = "regError";
		}else if(userService.validateUserName(username) != null){
			ActionContext.getContext().put("username_occupy", this.getText("username_occupy"));
			ActionContext.getContext().put("regUser", regUser);
			path = "regError";
		}else if(!sessionCode.equalsIgnoreCase(validateCode)){
			ActionContext.getContext().put("validateCodeError", this.getText("validateCodeError"));
			ActionContext.getContext().put("regUser", regUser);
			path = "regError";
		}else{
			
				User user = new User();
				user.setId(IDUtil.gernerateID());
				user.setEmail(email);
				user.setUserName(username);
				user.setUserPwd(MD5Util.MD5(userpwd));
				userService.userRegister(user);
				ActionContext.getContext().getSession().put("regUser", username);
				path = "success";
		}
		return path;
	}
	/**
	 * this method will let the login information validate then forward to the login page
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String loginOut() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession() != null)
			request.getSession().invalidate();
		return SUCCESS;
	}
	/**
	 * this method will invoke method sendEmail in SendEmailUtil to help user find the password
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String findPwdWithEmail() throws Exception {
		
		String sessionCode = (String) ActionContext.getContext().getSession().get("sessionCode");
		if(!"".equals(email) && !"".equals(validateCode)){
			if(userService.validateEmail(email) != null && sessionCode.equalsIgnoreCase(validateCode)){
				String code = RandomNumUtil.Instance().getString();
				boolean flag = SendEmailUtil.sendEmail(email, "找回登陆密码",
						"找回登陆密码的验证码为：" + code + "    特别提示，验证码在30分钟后失效。");
				if(flag){
					ActionContext.getContext().getSession().put("code", code);
					ActionContext.getContext().getSession().put("email", email);
					return SUCCESS;
				}else{
					ActionContext.getContext().put("emailInput", email);
					ActionContext.getContext().put("findPwdError", this.getText("send_email_error"));
					return "findPwdError";
				}
				
			}else{
				ActionContext.getContext().put("emailInput", email);
				ActionContext.getContext().put("findPwdError", this.getText("email_or_calidateCode_error"));
				return "findPwdError";
			}
		}else{
			ActionContext.getContext().put("findPwdError", this.getText("null_tip"));
			return "findPwdError";
		}
		
	    
	}
	/**
	 * this method will check the code from Email
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String findPwdNext() throws Exception {
		String code = (String) ActionContext.getContext().getSession().get("code");
		if(code.equalsIgnoreCase(validateCode)){
			return SUCCESS;
		}else{
			ActionContext.getContext().put("emailInput", email);
			ActionContext.getContext().put("findPwdError", this.getText("fill_error"));
			return "findPwdError";
		}
		
	}
	/**
	 * this method will reset password
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String resetPwd() throws Exception {
		
		email = (String) ActionContext.getContext().getSession().get("email");
		User user = new User();
		user.setEmail(email);
		user.setUserPwd(MD5Util.MD5(newpwd));
		userService.updateUser(user);
		return SUCCESS;
	}
	/**
	 * this method will judge whether the user name or email exists in the database
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String validateEmailOrUserName() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String flag = request.getParameter("flag");
		User user = new User();
		
		if("1".equals(flag)){
			user = userService.validateEmail(email);
			if(user != null)
				PrintWriterUtil.printWriter(response, "true");
		}else{
			user = userService.validateUserName(username);
			if(user != null)
				PrintWriterUtil.printWriter(response, "true");
		}
		return null;
	}
	/**
	 * this method will complete user info 
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String completeUserInfo() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		user.setInstitute(institute);
		user.setHomepage(homepage);
		user.setSelfIntroduction(selfIntroduction);
		if(userService.completeUserInfo(user)){
			ActionContext.getContext().put("completeError", this.getText("save_user_suc"));
			return SUCCESS;
		}else {
			ActionContext.getContext().put("completeError", this.getText("save_user_fail"));
			return "completeError";
		}
	}
	/**
	 * this method will invoke validateUserPwd method in service to judge whether the pwd exists in the database
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String validateUserPwd() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user != null){
			String userid = user.getId();
			user = userService.validateUserPwd(userid,MD5Util.MD5(userpwd));
			if(user == null)
				PrintWriterUtil.printWriter(response, "true");
		}
		return null;
	}
	/**
	 * this method will complete the modify for user pwd
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String modifyUserPwd() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		user.setUserPwd(MD5Util.MD5(newpwd));
		if(userService.modifyUserPwd(user)){
			ActionContext.getContext().put("modifyError", this.getText("modifypwd_suc"));
			return SUCCESS;
		}else {
			ActionContext.getContext().put("modifyError", this.getText("modifypwd_suc"));
			return "modifyError";
		}
	}
	/**
	 * this method will check whether the validateCode is right
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String checkValidateCode() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String sessionCode = (String) ActionContext.getContext().getSession().get("sessionCode");
		if(sessionCode.equalsIgnoreCase(validateCode)){
			PrintWriterUtil.printWriter(response, "true");
		}else {
			PrintWriterUtil.printWriter(response, "false");
		}
		return null;
	}
	

}
