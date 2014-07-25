package com.big.authorization.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.util.DBConnUtil;
import com.big.authorization.util.MD5Util;

public class ValidateLoginWS {
	private User user;
	private WebInfo webinfo;
	private AuthorizedWeb authorizedWeb;
	
	private User getUser(String userName, String userPwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from user where user_name = ? and user_pwd = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userName);
			ps.setString(2, MD5Util.MD5(userPwd));
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setRole(rs.getInt("role"));
				user.setEmail(rs.getString("email"));
				user.setHomepage(rs.getString("homepage"));
				user.setInstitute(rs.getString("institute"));
				user.setSelfIntroduction(rs.getString("self_Introduction"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPwd(rs.getString("user_pwd"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}

		return user;
	}

	private WebInfo getWeb(String webHost) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from web_info where web_host = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, webHost);
			rs = ps.executeQuery();
			while (rs.next()) {
				webinfo = new WebInfo();
				webinfo.setId(rs.getString("id"));
				webinfo.setWebName(rs.getString("web_name"));
				webinfo.setWebHost(rs.getString("web_host"));
				webinfo.setLogoUrl(rs.getString("logo_url"));
				webinfo.setBriefIntroduction(rs.getString("brief_introduction"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}

		return webinfo;
	}

	private AuthorizedWeb getAuthorizedWeb(WebInfo webinfo, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from authorized_web where web_id = ? and user_id = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, webinfo.getId());
			ps.setString(2, user.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				authorizedWeb = new AuthorizedWeb();
				authorizedWeb.setId(rs.getString("id"));
				authorizedWeb.setIsauthorized(rs.getInt("isauthorized"));
				authorizedWeb.setUser(user);
				authorizedWeb.setWeb(webinfo);
				authorizedWeb.setRemark(rs.getString("remark"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return authorizedWeb;
	}

	public String getValidateLoginWSResult(String userName, String userPwd, String webHost) {

		if (!"".equals(userName) && !"".equals(userPwd)) {

			User user = this.getUser(userName, userPwd);
			if(user != null && user.getRole() == 1) {
				return "Admin";

			}else if (user != null && user.getRole() == 0) {
				
				if(!"".equals(webHost)){
					WebInfo webinfo = this.getWeb(webHost);
					
					if (webinfo != null){
						AuthorizedWeb authorizedWeb = this.getAuthorizedWeb(webinfo, user);
						
						if(authorizedWeb != null && authorizedWeb.getIsauthorized() == 1){
							return "Authorized user";
						}else{
							return "No permission";
						}
					}else{
						return "No web";
					}
				}else{
					return "Error message";
				}
				
			}else{
				return "No user";
			}

		} else {
			return "Error message";
		}

	}

}
