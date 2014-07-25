/***********************************************************************
 * FileName:  WebapiInfoAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiInfoAction deal with front page displays  .
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

import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiInfo;
import com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI;
import com.big.authorization.service.serviceInterface.UserServiceI;
import com.big.authorization.service.serviceInterface.WebInfoServiceI;
import com.big.authorization.service.serviceInterface.WebapiInfoServiceI;
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
public class WebapiInfoAction extends ActionSupport{
	private WebapiInfoServiceI webapiInfoService;
	private String id;
	private String apiName;
	private String apiUrl;
	private String apiUsage;
	private String num;
	private Date addTime;
	private String[] ids;
	private AuthorizedWebapiServiceI authorizedWebapiService;
	private String flag;
	
	public WebapiInfoServiceI getWebapiInfoService() {
		return webapiInfoService;
	}

	public void setWebapiInfoService(WebapiInfoServiceI webapiInfoService) {
		this.webapiInfoService = webapiInfoService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiUsage() {
		return apiUsage;
	}

	public void setApiUsage(String apiUsage) {
		this.apiUsage = apiUsage;
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
	public AuthorizedWebapiServiceI getAuthorizedWebapiService() {
		return authorizedWebapiService;
	}

	public void setAuthorizedWebapiService(
			AuthorizedWebapiServiceI authorizedWebapiService) {
		this.authorizedWebapiService = authorizedWebapiService;
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
	public String webapiInfoList() throws Exception {

		int webapiInfoCount = webapiInfoService.webapiInfoCount(null);
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, webapiInfoCount);
		List<WebapiInfo> webapiInfoList = webapiInfoService.webapiInfoList(null, page.getStartRow(), page.getSize());
		List<AuthorizedWebapi> authorizedWebapiList = authorizedWebapiService.authorizedWebapiList(null, 0, 0);
		ActionContext.getContext().getSession().put("authorizedWebapiList", authorizedWebapiList);
		ActionContext.getContext().getSession().put("webapiInfoList", webapiInfoList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the web info via apiName
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String queryWebapiListByapiName() throws Exception {
		
		int webapiInfoCount = (Integer) webapiInfoService.webapiInfoCount(new Object[]{"apiName", apiName.trim()});
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, webapiInfoCount);
		List<WebapiInfo> webapiInfoList = webapiInfoService.webapiInfoList(new Object[]{"apiName", apiName.trim()}, page.getStartRow(), page.getSize());
		List<AuthorizedWebapi> authorizedWebapiList = authorizedWebapiService.authorizedWebapiList(null, 0, 0);
		ActionContext.getContext().getSession().put("authorizedWebapiList", authorizedWebapiList);
		ActionContext.getContext().getSession().put("webapiInfoList", webapiInfoList);
		ActionContext.getContext().getSession().put("page", page);
		return SUCCESS;
	}
	/**
	 * this method will get all the web info 
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String manageWebapiInfoList() throws Exception {
		return webapiInfoList();
	}
	/**
	 * this method will insert one webapiinfo to database
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String addWebapiInfo() throws Exception {
		if("modify".equals(flag)){
			modifyWebapiInfo();
			flag = "add";
		}else{
			
			WebapiInfo webapiinfo = new WebapiInfo();
			webapiinfo.setId(IDUtil.gernerateID());
			webapiinfo.setApiName(apiName);
			webapiinfo.setApiUrl(apiUrl);
			webapiinfo.setApiUsage(apiUsage);
			webapiinfo.setAddTime(new Date());
			if(webapiInfoService.addWebapiInfo(webapiinfo))
				ActionContext.getContext().put("WebapiInfoTip", this.getText("add_webapi_suc"));
			else
				ActionContext.getContext().put("WebapiInfoTip", this.getText("add_webapi_fail"));
			
			manageWebapiInfoList();
		}
		
		return SUCCESS;
	}
	/**
	 * this method will invoke deleteWebapiInfo in WebapiInfoService to delete one webapiinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String deleteWebapiInfo() throws Exception {
		if(webapiInfoService.deleteWebapiInfo(id))
			ActionContext.getContext().put("WebapiInfoTip", this.getText("delete_webapi_suc"));
		else
			ActionContext.getContext().put("WebapiInfoTip", this.getText("delete_webapi_fail"));
		
		manageWebapiInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke deleteWebapiInfos in WebapiInfoService to delete webapiinfos
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String deleteWebapiInfos() throws Exception {
		if(webapiInfoService.deleteWebapiInfos(ids))
			ActionContext.getContext().put("WebapiInfoTip", this.getText("delete_webapi_suc"));
		else
			ActionContext.getContext().put("WebapiInfoTip", this.getText("delete_webapi_fail"));
		
		manageWebapiInfoList();
		return SUCCESS;
	}
	/**
	 * this method will invoke modifyWebapiInfoPre in WebInfoService to get this webapiinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String modifyWebapiInfoPre() throws Exception {
		
		WebapiInfo webapiInfo = webapiInfoService.showWebapiInfoById(id);
		ActionContext.getContext().put("webapiInfo", webapiInfo);
		manageWebapiInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke modifyWebInfo in WebInfoService to modify one webinfo
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String modifyWebapiInfo() throws Exception {
		WebapiInfo webapiInfo = new WebapiInfo();
		webapiInfo.setId(id);
		webapiInfo.setApiName(apiName);
		webapiInfo.setApiUrl(apiUrl);
		webapiInfo.setApiUsage(apiUsage);
		webapiInfo.setAddTime(new Date());

		if(webapiInfoService.modifyWebapiInfo(webapiInfo))
			ActionContext.getContext().put("WebapiInfoTip", this.getText("modify_webapi_suc"));
		else
			ActionContext.getContext().put("WebapiInfoTip", this.getText("modify_webapi_fail"));
		
		manageWebapiInfoList();
		
		return SUCCESS;
	}
	/**
	 * this method will invoke showWebInfoById in WebInfoService to get one webinfo via id
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String showWebapiByWebapiId() throws Exception {
		WebapiInfo webapiInfo = webapiInfoService.showWebapiInfoById(id);
		ActionContext.getContext().put("webapiInfo", webapiInfo);
		return SUCCESS;
	}
	/**
	 * this method will send a email to admin
	 * @return : the logic page path
	 * @throws Exception
	 */
	public String remindWebapiAuthorisation() throws Exception {
		HttpServletResponse response =ServletActionContext.getResponse();
		String code = RandomNumUtil.Instance().getString();
		boolean flag = SendEmailUtil.sendEmail("396472720@qq.com","webApi授权申请","请尽快处理webAPI名称为："+apiName+" 的授权申请。编码：" + code);
		if(flag)
			PrintWriterUtil.printWriter(response, "true");
		else
			PrintWriterUtil.printWriter(response, "false");
		return null;
	}
}
