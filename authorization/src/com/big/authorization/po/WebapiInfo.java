/***********************************************************************
 * FileName:  WebapiInfo.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f4
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the webapi_info table in the authorization database.
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
 * @pdOid c0a7bea3-8d6a-468f-ae93-ddb24b3c2b4b */
public class WebapiInfo implements Serializable {
	/** @pdOid 78ce5c51-bac7-4d5b-8dc9-961f724bdab0 */
	private java.lang.String id;
	/** @pdOid 6e2fbb0c-9996-4c3e-b020-422db0dd2b9d */
	private java.lang.String apiName;
	/** @pdOid d0017385-151d-4ed4-a168-8ba491faa69f */
	private java.lang.String apiUrl;
	/** @pdOid cf885811-e70b-487f-8361-00b0e2bf6b6d */
	private java.lang.String apiUsage;
	/** @pdOid cf885811-e70b-487f-8361-00b0e2bf6b6d */
	private java.util.Date addTime;

	public WebapiInfo() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getApiName() {
		return apiName;
	}

	public void setApiName(java.lang.String apiName) {
		this.apiName = apiName;
	}

	public java.lang.String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(java.lang.String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public java.lang.String getApiUsage() {
		return apiUsage;
	}

	public void setApiUsage(java.lang.String apiUsage) {
		this.apiUsage = apiUsage;
	}

	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	
   
}