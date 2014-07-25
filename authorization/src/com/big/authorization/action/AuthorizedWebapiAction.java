/***********************************************************************
 * FileName:  AuthorizedWebapiAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is AuthorizedWebapiAction deal with front page displays  .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;
import org.apache.struts2.ServletActionContext;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.util.Constants;
import com.big.authorization.util.IDUtil;
import com.big.authorization.util.PageUtil;
import com.big.authorization.util.PrintWriterUtil;
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
public class AuthorizedWebapiAction extends ActionSupport{
	private AuthorizedWebapiServiceI authorizedWebapiService;
	private String id;
	private int isauthorized = 0;
	private String hostUrl;
	private int limitRequest = 1;
	private String remark;
	private WebapiInfo webapi;
	private User user;
	private String num;
	
	public AuthorizedWebapiServiceI getAuthorizedWebapiService() {
		return authorizedWebapiService;
	}


	public void setAuthorizedWebapiService(
			AuthorizedWebapiServiceI authorizedWebapiService) {
		this.authorizedWebapiService = authorizedWebapiService;
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


	public String getHostUrl() {
		return hostUrl;
	}


	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}


	public int getLimitRequest() {
		return limitRequest;
	}


	public void setLimitRequest(int limitRequest) {
		this.limitRequest = limitRequest;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public WebapiInfo getWebapi() {
		return webapi;
	}


	public void setWebapi(WebapiInfo webapi) {
		this.webapi = webapi;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	/**
	 * this method will get all the authorized webapi info 
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String authorizedWebapiList() throws Exception {
		int authorizedWebapiCount = authorizedWebapiService.authorizedWebapiCount(null);
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebapiCount);
		List<AuthorizedWebapi> authorizedWebapiList = authorizedWebapiService.authorizedWebapiList(null, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebapiList", authorizedWebapiList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the authorized webapi info via isauthorized
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String queryAuthorizedWebapiListByAuthorization() throws Exception {
		int authorizedWebapiCount = authorizedWebapiService.authorizedWebapiCount(new Object[]{"isauthorized", isauthorized});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebapiCount);
		List<AuthorizedWebapi> authorizedWebapiList = authorizedWebapiService.authorizedWebapiList(new Object[]{"isauthorized", isauthorized}, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebapiList", authorizedWebapiList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will judge if the webapi authorized
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String judgeIsAuthorizedByWebapiId() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String webapiId = (String) ServletActionContext.getRequest().getParameter("webapiId");
		String userid = ((User)ActionContext.getContext().getSession().get("user")).getId();
		AuthorizedWebapi authorizedWebapi = authorizedWebapiService.getAuthorizedWebByWebapiId(webapiId, userid);
		if(authorizedWebapi != null && authorizedWebapi.getIsauthorized() == 1)
			PrintWriterUtil.printWriter(response, "true1");
		else if(authorizedWebapi != null && authorizedWebapi.getIsauthorized() == 0)
			PrintWriterUtil.printWriter(response, "true0");
		else if(authorizedWebapi != null && authorizedWebapi.getIsauthorized() == 2)
			PrintWriterUtil.printWriter(response, "true2");
		else
			PrintWriterUtil.printWriter(response, "false");
		
		return null;
	}
	/**
	 * this method will get all the authorized webapi list via userid
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String showWebapiResourceListByUser() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		String userid = user.getId();
		int authorizedWebapiCountByUserId = authorizedWebapiService.authorizedWebapiCount(new Object[]{"userid",userid});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, authorizedWebapiCountByUserId);
		List<AuthorizedWebapi> authorizedWebapiListByUserId = authorizedWebapiService.authorizedWebapiList(new Object[]{"userid",userid}, page.getStartRow(), page.getSize());
		ActionContext.getContext().getSession().put("authorizedWebapiListByUserId", authorizedWebapiListByUserId);
		ActionContext.getContext().getSession().put("page", page);
	
		return SUCCESS;
		
	}
	/**
	 * this method will insert the webapiid and userid to the authorizedWebapi table
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String applyForWebapiAuthorisation() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) ActionContext.getContext().getSession().get("user");
		String userid = user.getId();
		String webapiId = request.getParameter("webapiId");
		if("applyAgain".equals(request.getParameter("flag"))){
			authorizedWebapiService.updateAuthorizedWebapi(new Object[]{"applyAgain",webapiId, userid});
			WebUtil.alert(request, response,this.getText("send_apply_already") , "/user/webapiresourcelist.jsp", false);
			return showWebapiResourceListByUser();
		}else{
			AuthorizedWebapi authorizedWebapi = new AuthorizedWebapi();
			authorizedWebapi.setId(IDUtil.gernerateID());
			authorizedWebapi.setIsauthorized(0);
			authorizedWebapi.setHostUrl(hostUrl);
			int re = authorizedWebapiService.applyForWebapiAuthorisation(authorizedWebapi, webapiId, userid);
			if(re != 0){
				SendEmailUtil.sendEmail("396472720@qq.com", "webApi授权申请",
						"请尽快处理webApi的名称为：" + request.getParameter("apiName")
								+ " 的授权申请。申请人：" + user.getUserName());
				PrintWriterUtil.printWriter(response, "true");
			}
			return null;
		}
	}
	/**
	 * this method will update the authorizedWebapi's limit requestTimes
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String setWebapiLimitRequestTimes() throws Exception {
		authorizedWebapiService.updateAuthorizedWebapi(new Object[]{"limitRequest", limitRequest, id});
		return null;
	}
	/**
	 * this method will update the authorizedWebapi's authoriation status
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String setWebapiAuthorization() throws Exception {
		authorizedWebapiService.updateAuthorizedWebapi(new Object[]{"isauthorized", isauthorized, remark, id});
		return null;
	}
}
