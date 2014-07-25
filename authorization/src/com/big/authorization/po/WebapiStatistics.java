/***********************************************************************
 * FileName:  WebapiStatistics.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f7
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the webapi_statistics table in the authorization database.
 * Version：0.1.0
 ***********************************************************************/
package com.big.authorization.po;
import java.io.Serializable;
import java.util.*;

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
 * @pdOid 21951271-d6e0-49d4-b5cc-b06caf59e2cf */
public class WebapiStatistics implements Serializable {
	/** @pdOid d5c43907-23f1-4b7b-9e40-b1449c01757f */
	private java.lang.String id;
	/** @pdOid 11e822e2-49fb-4b6e-97ae-04eed210b082 */
	private java.util.Date requestDate;
	/** @pdOid 9f4a0db4-effd-45d4-880b-3c3edf55ecee */
	private int requestTimes;
	/** @pdOid 714abbe3-d27a-4325-9f9e-a9c0f70ca17e */
	private AuthorizedWebapi authorizedWebapi;
	
	public WebapiStatistics() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.util.Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(java.util.Date requestDate) {
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
	
}