/***********************************************************************
 * FileName:  WebAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebAction deal with front page displays  .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.WebInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.util.Constants;
import com.big.authorization.util.IDUtil;
import com.big.authorization.util.PageUtil;
import com.big.authorization.util.PrintWriterUtil;
import com.big.authorization.util.RandomNumUtil;
import com.big.authorization.util.SendEmailUtil;
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
public class WebInfoAction extends ActionSupport{
	private WebInfoServiceI webInfoService;
	private String id;
	private String webName;
	private String webHost;
	private String logoUrl;
	private String briefIntroduction;
	private String num;
	private Date addTime;
	private String[] ids;
	private AuthorizedWebServiceI authorizedWebService;
	private String flag;
	
	public WebInfoServiceI getWebInfoService() {
		return webInfoService;
	}
	public void setWebInfoService(WebInfoServiceI webInfoService) {
		this.webInfoService = webInfoService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getWebHost() {
		return webHost;
	}
	public void setWebHost(String webHost) {
		this.webHost = webHost;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public AuthorizedWebServiceI getAuthorizedWebService() {
		return authorizedWebService;
	}
	public void setAuthorizedWebService(AuthorizedWebServiceI authorizedWebService) {
		this.authorizedWebService = authorizedWebService;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * this method will get all the web info 
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String webInfoList() throws Exception {

		int webinfoCount = webInfoService.webInfoCount(null);
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, webinfoCount);
		List<WebInfo> webinfoList = webInfoService.webInfoList(null, page.getStartRow(), page.getSize());
		List<AuthorizedWeb> authorizedWebList = authorizedWebService.authorizedWebList(null, 0, 0);
		ActionContext.getContext().getSession().put("authorizedWebList", authorizedWebList);
		ActionContext.getContext().getSession().put("webinfoList", webinfoList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the web info via webName
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String queryWebInfoListByWebName() throws Exception {
		
		int webinfoCount = webInfoService.webInfoCount(new Object[]{"webName", webName.trim()});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, webinfoCount);
		List<WebInfo> webinfoList = webInfoService.webInfoList(new Object[]{"webName", webName.trim()}, page.getStartRow(), page.getSize());
		List<AuthorizedWeb> authorizedWebList = authorizedWebService.authorizedWebList(null, 0, 0);
		ActionContext.getContext().getSession().put("authorizedWebList", authorizedWebList);
		ActionContext.getContext().getSession().put("webinfoList", webinfoList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the web info then manage this list
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String manageWebInfoList() throws Exception {
		
		return webInfoList();
	}
	/**
	 * this method will insert one webinfo to database
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String addWebInfo() throws Exception {
		if("modify".equals(flag)){
			modifyWebInfo();
			flag = "add";
		}else{
			
			WebInfo webinfo = new WebInfo();
			webinfo.setId(IDUtil.gernerateID());
			webinfo.setBriefIntroduction(briefIntroduction);
			webinfo.setLogoUrl(logoUrl);
			webinfo.setWebHost(webHost);
			webinfo.setWebName(webName);
			webinfo.setAddTime(new Date());
			if(webInfoService.addWebInfo(webinfo))
				ActionContext.getContext().put("WebInfoTip", this.getText("add_web_suc"));
			else
				ActionContext.getContext().put("WebInfoTip", this.getText("add_webapi_fail"));
			
			manageWebInfoList();
			
		}

		return SUCCESS;
	}
	/**
	 * this method will invoke deleteWebInfo in WebInfoService to delete one webinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String deleteWebInfo() throws Exception {
		if(webInfoService.deleteWebInfo(id))
			ActionContext.getContext().put("WebInfoTip", this.getText("delete_web_suc"));
		else
			ActionContext.getContext().put("WebInfoTip", this.getText("delete_web_fail"));
		
		manageWebInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke deleteWebInfo in WebInfoService to delete one webinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String deleteWebInfos() throws Exception {
		if(webInfoService.deleteWebInfos(ids))
			ActionContext.getContext().put("WebInfoTip", this.getText("delete_web_suc"));
		else
			ActionContext.getContext().put("WebInfoTip", this.getText("delete_web_fail"));
		
		manageWebInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke modifyWebInfoPre in WebInfoService to get this webinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String modifyWebInfoPre() throws Exception {
		
		WebInfo webinfo = webInfoService.showWebInfoById(id);
		ActionContext.getContext().put("webinfo", webinfo);
		manageWebInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke modifyWebInfo in WebInfoService to modify one webinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String modifyWebInfo() throws Exception {
		WebInfo webinfo = new WebInfo();
		webinfo.setAddTime(new Date());
		webinfo.setBriefIntroduction(briefIntroduction);
		webinfo.setId(id);
		webinfo.setLogoUrl(logoUrl);
		webinfo.setWebName(webName);
		webinfo.setWebHost(webHost);
		if(webInfoService.modifyWebInfo(webinfo))
			ActionContext.getContext().put("WebInfoTip", this.getText("modify_web_suc"));
		else
			ActionContext.getContext().put("WebInfoTip", this.getText("modify_web_fail"));
		
		manageWebInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke showWebInfoById in WebInfoService to get one webinfo via id
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String showWebInfoByWebId() throws Exception {
		WebInfo webinfo = webInfoService.showWebInfoById(id);
		ActionContext.getContext().put("webinfo", webinfo);
		return SUCCESS;
	}
	/**
	 * this method will send a email to admin
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String remindWebAuthorisation() throws Exception {
		HttpServletResponse response =ServletActionContext.getResponse();
		String code = RandomNumUtil.Instance().getString();
		boolean flag = SendEmailUtil.sendEmail("396472720@qq.com","web授权申请","请尽快处理web站点名称为："+webName+" 的授权申请。编码：" + code);
		if(flag)
			PrintWriterUtil.printWriter(response, "true");
		else
			PrintWriterUtil.printWriter(response, "false");
		return null;
	}

}
