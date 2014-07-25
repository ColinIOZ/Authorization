/***********************************************************************
 * FileName:  Users.java
 * CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * FileID：f1
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified by：
 * Modified Date：
 * Comments：This class corresponding to the users table in the authorization database.
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
 * @pdOid 025eb85c-2bf4-4757-bcdc-56d1c5b535e5 */
public class User implements Serializable {
	/** @pdOid ba2ca802-3614-4676-a187-594efd37e968 */
	private java.lang.String id;
	/** @pdOid 33780572-1a98-41dd-8e5b-a50174f1725a */
	private java.lang.String userName;
	/** @pdOid 7e6985f7-8c88-4b83-bb23-a5ec8b1f4a6e */
	private java.lang.String userPwd;
	/** @pdOid 7e6985f7-8c88-4b83-bb23-a5ec8b1f4a6e */
	private java.lang.String email;
	/** @pdOid 09b8652d-f13f-429d-9a07-01b2e3d45b94 */
	private java.lang.String institute;
	/** @pdOid ce0269b5-2530-472e-9556-2803b5008fa1 */
	private java.lang.String homepage;
	/** @pdOid ba4a084d-26b8-4516-bc7b-b00a9ab58cab */
	private java.lang.String selfIntroduction;
	/** @pdOid dc01917d-2ee4-44c9-bd5b-d0db2d2f6ab9 */
	private int role = 0;

	public User() {
		super();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(java.lang.String userPwd) {
		this.userPwd = userPwd;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getInstitute() {
		return institute;
	}

	public void setInstitute(java.lang.String institute) {
		this.institute = institute;
	}

	public java.lang.String getHomepage() {
		return homepage;
	}

	public void setHomepage(java.lang.String homepage) {
		this.homepage = homepage;
	}

	public java.lang.String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(java.lang.String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}