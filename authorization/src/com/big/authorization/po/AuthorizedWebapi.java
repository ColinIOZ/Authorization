/***********************************************************************
 * FileName:  AuthorizedWebapi.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f6
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the authorized_webapi table in the authorization database.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.po;

import java.io.Serializable;

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
 * 
 * @pdOid ba6d52e2-9d6b-4ce3-b4d8-6282ee32b778 */
public class AuthorizedWebapi implements Serializable {
	/**
	 * key of the object
	 * 
	 * @pdOid efdc516a-72b7-45c6-8f0f-67527c09ea76
	 */
	private java.lang.String id;
	/**
	 * status of authorization,0==no,1==yes
	 * 
	 * @pdOid abfe739d-b440-41b8-b28c-420345edeec4
	 */
	private int isauthorized = 0;
	/**
	 * the host url who whant to visit the api
	 * 
	 * @pdOid cfe47ab6-af12-4e4f-a7ce-944af4b6f901
	 */
	private java.lang.String hostUrl;
	/**
	 * limited request each day
	 * 
	 * @pdOid 194ae63a-d2a2-476b-9822-cdc49776bf49
	 */
	private int limitRequest = 1;
	/**
	 * other needed infromaton
	 * 
	 * @pdOid 6b127c65-efb3-4283-8bc5-098772bbffae
	 */
	private java.lang.String remark;
	/** @pdOid e98ec9eb-f2e2-4972-9dd1-6120d8e1fc22 */
	private WebapiInfo webapi;
	/** @pdOid 4fe0e137-6054-43a5-9ecd-6136b1b6596d */
	private User user;

	public AuthorizedWebapi() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public int getIsauthorized() {
		return isauthorized;
	}

	public void setIsauthorized(int isauthorized) {
		this.isauthorized = isauthorized;
	}

	public java.lang.String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(java.lang.String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public int getLimitRequest() {
		return limitRequest;
	}

	public void setLimitRequest(int limitRequest) {
		this.limitRequest = limitRequest;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
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
   
}