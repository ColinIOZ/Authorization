/***********************************************************************
 * FileName:  AuthorizedWebAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebAction deal with front page displays  .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;
import org.apache.struts2.ServletActionContext;

import sun.print.resources.serviceui;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.util.Constants;
import com.big.authorization.util.IDUtil;
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
public class AuthorizedWebAction extends ActionSupport{
	private AuthorizedWebServiceI authorizedWebService;
	private String id;
	private int isauthorized = 0;
	private String remark;
	private User user;
	private WebInfo web;
	private String num;
	
	public AuthorizedWebServiceI getAuthorizedWebService() {
		return authorizedWebService;
	}
	public void setAuthorizedWebService(AuthorizedWebServiceI authorizedWebService) {
		this.authorizedWebService = authorizedWebService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsauthorized() {
		return isauthorized;
	}
	public void setIsauthorized(int isauthorized) {
		this.isauthorized = isauthorized;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public WebInfo getWeb() {
		return web;
	}
	public void setWeb(WebInfo web) {
		this.web = web;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * this method will get all the authorized web info 
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String authorizedWebList() throws Exception {
		int authorizedWebCount = authorizedWebService.authorizedWebCount(null);
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebCount);
		List<AuthorizedWeb> authorizedWebList = authorizedWebService.authorizedWebList(null, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebList", authorizedWebList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the authorized web info via isauthorized
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String queryAuthorizedWebListByAuthorization() throws Exception {
		int authorizedWebCount = authorizedWebService.authorizedWebCount(new Object[]{"isauthorized", isauthorized});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebCount);
		List<AuthorizedWeb> authorizedWebList = authorizedWebService.authorizedWebList(new Object[]{"isauthorized", isauthorized}, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebList", authorizedWebList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will judge if the web info authorized
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String judgeIsAuthorizedByWebId() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String webid = (String) ServletActionContext.getRequest().getParameter("webid");
		String userid = ((User)ActionContext.getContext().getSession().get("user")).getId();
		AuthorizedWeb authorizedWeb = authorizedWebService.getAuthorizedWebByWebId(webid, userid);
		if(authorizedWeb != null && authorizedWeb.getIsauthorized() == 1)
			PrintWriterUtil.printWriter(response, "true1");
		else if(authorizedWeb != null && authorizedWeb.getIsauthorized() == 0)
			PrintWriterUtil.printWriter(response, "true0");
		else if(authorizedWeb != null && authorizedWeb.getIsauthorized() == 2)
			PrintWriterUtil.printWriter(response, "true2");
		else
			PrintWriterUtil.printWriter(response, "false");
		
		return null;
	}
	/**
	 * this method will get all the authorized web list via userid
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String showWebResourceListByUser() throws Exception {
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		String userid = user.getId();
		int authorizedWebCountByUserId = authorizedWebService.authorizedWebCount(new Object[]{"userid",userid});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebCountByUserId);
		List<AuthorizedWeb> authorizedWebListByUserId = authorizedWebService.authorizedWebList(new Object[]{"userid",userid}, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebListByUserId", authorizedWebListByUserId);
		ActionContext.getContext().getSession().put("page", page);

		return SUCCESS;
		
	}
	/**
	 * this method will insert the webid and userid to the authorizedWeb table
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String applyForWebAuthorisation() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) ActionContext.getContext().getSession().get("user");
		String userid = user.getId();
		String webid = request.getParameter("webid");
		if("applyAgain".equals(request.getParameter("flag"))){
			authorizedWebService.updateAuthorizedWeb(new Object[]{"applyAgain", webid,userid});
			WebUtil.alert(request, response,this.getText("send_apply_already") , "/user/webresourcelist.jsp", false);
			return showWebResourceListByUser();
		}else{
			AuthorizedWeb authorizedWeb = new AuthorizedWeb();
			authorizedWeb.setId(IDUtil.gernerateID());
			authorizedWeb.setIsauthorized(0);
			int re = authorizedWebService.applyForWebAuthorisation(authorizedWeb, webid, userid);
			if(re != 0){
				SendEmailUtil.sendEmail("396472720@qq.com", "web授权申请",
						"请尽快处理web站点名称为：" + request.getParameter("webName")
								+ " 的授权申请。申请人：" + user.getUserName());
				PrintWriterUtil.printWriter(response, "true");
			}
			return null;
		}
		
	}
	/**
	 * this method will update the authorizedWeb's authoriation status
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String setWebAuthorization() throws Exception {
		authorizedWebService.updateAuthorizedWeb(new Object[]{"isauthorized", isauthorized, remark, id});
		return null;
	}

}
