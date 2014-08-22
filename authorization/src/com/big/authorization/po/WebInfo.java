/***********************************************************************
 * FileName:  WebInfo.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f3
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the web_info table in the authorization database.
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
 * @pdOid 8922eb08-c370-46b3-80fe-324180123f39 */
public class WebInfo implements Serializable {
	/** @pdOid 9e9a89f5-0c88-48fe-9982-6e5a9105b2b9 */
	private java.lang.String id;
	/** @pdOid 0511c087-1691-4bee-9818-4147b5a586f6 */
	private java.lang.String webName;
	/** @pdOid 701929c5-6e7c-4f69-a35a-a40adc6d3eb6 */
	private java.lang.String webHost;
	/** @pdOid c8e2b84c-e869-4f64-88e3-02faaea1d040 */
	private java.lang.String logoUrl;
	/** @pdOid 2eae831e-de02-4410-9f25-ddb7269277d2 */
	private java.lang.String briefIntroduction;
	/** @pdOid cf885811-e70b-487f-8361-00b0e2bf6b6d */
	private java.util.Date addTime;
	
	public WebInfo() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getWebName() {
		return webName;
	}

	public void setWebName(java.lang.String webName) {
		this.webName = webName;
	}

	public java.lang.String getWebHost() {
		return webHost;
	}

	public void setWebHost(java.lang.String webHost) {
		this.webHost = webHost;
	}

	public java.lang.String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(java.lang.String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public java.lang.String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(java.lang.String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	
}