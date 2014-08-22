/***********************************************************************
 * FileName:  AuthorizedWeb.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f5
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the authorizaed_web table in the authorization database.
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
 * @pdOid 385a4707-0656-4415-8770-5d3d21170f28 */
public class AuthorizedWeb implements Serializable{
	/**
	 * key of this object
	 * 
	 * @pdOid 505772e7-04f8-48f6-8d84-7fce73bee511
	 */
	private java.lang.String id;
	/**
	 * status of authorization
	 * 
	 * @pdOid 51dff7f9-5648-4891-960b-2b883cbf7a39
	 */
	private int isauthorized = 0;
	/**
	 * other information
	 * 
	 * @pdOid f154c5e4-1e76-4c79-9487-a4a330ee84b3
	 */
	private java.lang.String remark;
	/** @pdOid ba63841e-4cea-433e-8155-5e17058abd80 */
	private User user;
	/** @pdOid 525d1982-7d5b-468d-9ade-80388370dcd9 */
	private WebInfo web;

	public AuthorizedWeb() {
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

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void setWeb(WebInfo web) {
		this.web = web;
	}

	public WebInfo getWeb() {
		return web;
	}
	
}