/***********************************************************************
 * FileName:  Exports.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f2
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the exports table in the authorization database.
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
 * @pdOid a639ec59-9129-47b7-9a12-028aeb4c9db9 */
public class Export implements Serializable {
	/** @pdOid b6b83a52-b78f-4836-a269-68972e7f54b3 */
	private java.lang.String id;
	/** @pdOid da3ca226-160d-48d1-a810-35af0f0ff7f2 */
	private java.lang.String nameEn;
	/** @pdOid 9599c17d-ba97-4b72-a45d-297b374c690a */
	private java.lang.String nameCn;
	/** @pdOid fd4a85e7-cef1-4c9e-84a2-de05cd409e0f */
	private java.lang.String instituteEn;
	/** @pdOid c0ca4165-60e5-404f-abdb-6141668738a0 */
	private java.lang.String instituteCn;
	/** @pdOid f10ab393-105c-43e1-853d-d79bd89e6d46 */
	private java.lang.String addressEn;
	/** @pdOid bb65521e-ab75-48e7-b419-69e899e776da */
	private java.lang.String addressCn;
	/** @pdOid 8994c20a-ddc0-47f5-8b72-093d3b194041 */
	private java.lang.String postcode;
	/** @pdOid d8798650-b631-42c4-9e4e-ae5ff83add73 */
	private java.lang.String homepageEn;
	/** @pdOid ac22773f-998a-40cd-993b-c06c94481c61 */
	private java.lang.String homepageCn;
	/** @pdOid 428b3bc4-9ca8-4318-a1fe-9a1d271ed8d6 */
	private java.lang.String domain;
	/** @pdOid e96baf68-1acf-41de-862d-bbd34c4d14e9 */
	private User user;

	public Export() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getNameEn() {
		return nameEn;
	}

	public void setNameEn(java.lang.String nameEn) {
		this.nameEn = nameEn;
	}

	public java.lang.String getNameCn() {
		return nameCn;
	}

	public void setNameCn(java.lang.String nameCn) {
		this.nameCn = nameCn;
	}

	public java.lang.String getInstituteEn() {
		return instituteEn;
	}

	public void setInstituteEn(java.lang.String instituteEn) {
		this.instituteEn = instituteEn;
	}

	public java.lang.String getInstituteCn() {
		return instituteCn;
	}

	public void setInstituteCn(java.lang.String instituteCn) {
		this.instituteCn = instituteCn;
	}

	public java.lang.String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(java.lang.String addressEn) {
		this.addressEn = addressEn;
	}

	public java.lang.String getAddressCn() {
		return addressCn;
	}

	public void setAddressCn(java.lang.String addressCn) {
		this.addressCn = addressCn;
	}

	public java.lang.String getPostcode() {
		return postcode;
	}

	public void setPostcode(java.lang.String postcode) {
		this.postcode = postcode;
	}

	public java.lang.String getHomepageEn() {
		return homepageEn;
	}

	public void setHomepageEn(java.lang.String homepageEn) {
		this.homepageEn = homepageEn;
	}

	public java.lang.String getHomepageCn() {
		return homepageCn;
	}

	public void setHomepageCn(java.lang.String homepageCn) {
		this.homepageCn = homepageCn;
	}

	public java.lang.String getDomain() {
		return domain;
	}

	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
   
}