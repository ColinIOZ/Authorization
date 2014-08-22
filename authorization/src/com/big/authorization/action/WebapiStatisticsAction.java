/***********************************************************************
 * FileName:  WebapiStatisticsAction.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class is WebapiStatisticsAction deal with front page displays  .
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebapiStatistics;
import com.big.authorization.service.serviceImplements.WebapiStatisticsServiceImpl;
import com.big.authorization.service.serviceInterface.AuthorizedWebapiServiceI;
import com.big.authorization.util.Constants;
import com.big.authorization.util.IDUtil;
import com.big.authorization.util.PageUtil;
import com.big.authorization.util.PrintWriterUtil;
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
public class WebapiStatisticsAction extends ActionSupport{
	private WebapiStatisticsServiceImpl webapiStatisticsService;
	private AuthorizedWebapiServiceI authorizedWebapiService;
	private String id;
	private Date requestDate;
	private int requestTimes;
	private AuthorizedWebapi authorizedWebapi;
	private String num;
	
	public WebapiStatisticsServiceImpl getWebapiStatisticsService() {
		return webapiStatisticsService;
	}
	public void setWebapiStatisticsService(
			WebapiStatisticsServiceImpl webapiStatisticsService) {
		this.webapiStatisticsService = webapiStatisticsService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getRequestTimes() {
		return requestTimes;
	}
	public void setRequestTimes(int requestTimes) {
		this.requestTimes = requestTimes;
	}
	public AuthorizedWebapi getAuthorizedWebapi() {
		return authorizedWebapi;
	}
	public void setAuthorizedWebapi(AuthorizedWebapi authorizedWebapi) {
		this.authorizedWebapi = authorizedWebapi;
	}
	public AuthorizedWebapiServiceI getAuthorizedWebapiService() {
		return authorizedWebapiService;
	}
	public void setAuthorizedWebapiService(
			AuthorizedWebapiServiceI authorizedWebapiService) {
		this.authorizedWebapiService = authorizedWebapiService;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * this method will invoke countWebapiRequestTimes in WebapiStatisticsService to update the requestTimes
	 * @return : the logic path
	 * @throws Exception
	 */
	public String countWebapiRequestTimes() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String webapiId = (String) ServletActionContext.getRequest()
				.getParameter("webapiId");
		String userid = ((User) ActionContext.getContext().getSession()
				.get("user")).getId();
		AuthorizedWebapi authorizedWebapi = authorizedWebapiService
				.getAuthorizedWebByWebapiId(webapiId, userid);
		WebapiStatistics webapiStatistics = webapiStatisticsService
				.getWebapiStatisticsByauthorizedWebapiId(authorizedWebapi.getId());
		Date newDay = new Date();
		if(webapiStatistics == null){
			webapiStatistics = new WebapiStatistics();
			webapiStatistics.setId(IDUtil.gernerateID());
			webapiStatistics.setAuthorizedWebapi(authorizedWebapi);
			webapiStatistics.setRequestDate(newDay);
			webapiStatistics.setRequestTimes(1);
			webapiStatisticsService.saveWebapiStatistics(webapiStatistics);
			
		}else{
			if(DateFormat.getDateInstance().format(webapiStatistics.getRequestDate()) 
					.equals(DateFormat.getDateInstance().format(newDay))){
				
				if(webapiStatistics.getRequestTimes() >= authorizedWebapi.getLimitRequest())
					PrintWriterUtil.printWriter(response, "limit");
				else
					webapiStatisticsService.updateWebapiStatistics(new Object[]{authorizedWebapi.getId()});
			}else{
				webapiStatisticsService.updateWebapiStatistics(new Object[]{"newDay", newDay, 1, authorizedWebapi.getId()});
			}
		}
			
		return null;
	}
	
	/**
	 * this method will invoke webapiStatisticsList in WebapiStatisticsService to get webapiStatisticsList
	 * @return : the logic path
	 * @throws Exception
	 */
	public String webapiStatisticsList() throws Exception {
		int webapiStatisticsCount = webapiStatisticsService.getWebapiStatisticsCount(null);
		PageUtil page = new PageUtil(Constants.PAGE_SIZE, num, webapiStatisticsCount);
		List<WebapiStatistics> webapiStatisticsList = webapiStatisticsService.getWebapiStatisticsList(null,page.getStartRow(),page.getSize());
		
		ActionContext.getContext().getSession().put("page", page);
		ActionContext.getContext().getSession().put("webapiStatisticsList", webapiStatisticsList);
		return SUCCESS;
	}
}
