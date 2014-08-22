package com.big.authorization.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import com.big.authorization.po.AuthorizedWebapi;
import com.big.authorization.po.User;
import com.big.authorization.po.WebapiInfo;
import com.big.authorization.po.WebapiStatistics;
import com.big.authorization.util.DBConnUtil;
import com.big.authorization.util.IDUtil;

public class ValidateAPIWS {
	private User user;
	private WebapiInfo webapi;
	private AuthorizedWebapi authorizedWebapi;
	private WebapiStatistics webapiStatistics;
	
	private User getUser(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from user where id = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userid);
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

	private WebapiInfo getWebapi(String webapiId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from webapi_info where id = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, webapiId);
			rs = ps.executeQuery();
			while (rs.next()) {
				webapi = new WebapiInfo();
				webapi.setId(rs.getString("id"));
				webapi.setAddTime(rs.getDate("add_time"));
				webapi.setApiName(rs.getString("api_name"));
				webapi.setApiUrl(rs.getString("api_url"));
				webapi.setApiUsage(rs.getString("api_usage"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}

		return webapi;
	}

	private AuthorizedWebapi getAuthorizedWebapi(String authorizedWebapiId, String hostUrl) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from authorized_webapi where id = ? and host_url = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, authorizedWebapiId);
			ps.setString(2, hostUrl);
			rs = ps.executeQuery();
			while (rs.next()) {
				authorizedWebapi = new AuthorizedWebapi();
				authorizedWebapi.setId(rs.getString("id"));
				authorizedWebapi.setHostUrl(rs.getString("host_url"));
				authorizedWebapi.setIsauthorized(rs.getInt("isauthorized"));
				authorizedWebapi.setLimitRequest(rs.getInt("limit_request"));
				authorizedWebapi.setRemark(rs.getString("remark"));
				authorizedWebapi.setUser(this.getUser(rs.getString("user_id")));
				authorizedWebapi.setWebapi(this.getWebapi(rs.getString("webapi_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return authorizedWebapi;
	}
	private WebapiStatistics getWebapiStatistics(String authorizedWebapiId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"select * from webapi_statistics where authorized_webapi_id = ?");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, authorizedWebapiId);
			rs = ps.executeQuery();
			while (rs.next()) {
				webapiStatistics = new WebapiStatistics();
				webapiStatistics.setId(rs.getString("id"));
				webapiStatistics.setAuthorizedWebapi(authorizedWebapi);
				webapiStatistics.setRequestDate(rs.getDate("request_date"));
				webapiStatistics.setRequestTimes(rs.getInt("request_times"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return webapiStatistics;
	}
	private boolean saveWebapiStatistics( WebapiStatistics webapiStatistics) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
		"insert webapi_statistics(id, authorized_webapi_id, request_date, request_times) values (?, ?, ?, ?)");
		try {
			conn = DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, webapiStatistics.getId());
			ps.setString(2, webapiStatistics.getAuthorizedWebapi().getId());
			ps.setObject(3, webapiStatistics.getRequestDate());
			ps.setInt(4, webapiStatistics.getRequestTimes());
			if(ps.executeUpdate() != 0)
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return flag;
	}
	private boolean updateWebapiStatistics(String authorizedWebapiId, String day) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		try {
			conn = DBConnUtil.getConnection();
			if(day.endsWith("newDay")){
				sql = new StringBuilder(
					"update webapi_statistics set request_date = ?, request_times = 1 where authorized_webapi_id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setObject(1, new Date());
				ps.setString(2, authorizedWebapiId); 
			}else{
				sql = new StringBuilder(
					"update webapi_statistics set request_times = (request_times + 1) where authorized_webapi_id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, authorizedWebapiId);
			}
			
			if(ps.executeUpdate() != 0)
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return flag;
	}
	public String getValidateAPIWSResult(String authorizedWebapiId, String hostUrl) {

		if (!"".equals(authorizedWebapiId) && !"".equals(hostUrl)) {
			AuthorizedWebapi authorizedWebapi = this.getAuthorizedWebapi(authorizedWebapiId, hostUrl);
			if(authorizedWebapi != null){
				if(authorizedWebapi.getIsauthorized() == 0){
					return "Wait authorize";
				}else if(authorizedWebapi.getIsauthorized() == 1){
					WebapiStatistics webapiStatistics = this.getWebapiStatistics(authorizedWebapi.getId());
					Date newDay = new Date();
					if(webapiStatistics == null){
						webapiStatistics = new WebapiStatistics();
						webapiStatistics.setId(IDUtil.gernerateID());
						webapiStatistics.setAuthorizedWebapi(authorizedWebapi);
						webapiStatistics.setRequestDate(newDay);
						webapiStatistics.setRequestTimes(1);
						this.saveWebapiStatistics(webapiStatistics);
						return "Agree authorize";
						
					}else{
						if(DateFormat.getDateInstance().format(webapiStatistics.getRequestDate()) 
								.equals(DateFormat.getDateInstance().format(newDay))){
							
							if(webapiStatistics.getRequestTimes() >= authorizedWebapi.getLimitRequest())
								return "RequestTimes greater than LimitRequestTimes";
							else
								this.updateWebapiStatistics(authorizedWebapi.getId(), "sameDay");
								return "Agree authorize";
						}else{
							this.updateWebapiStatistics(authorizedWebapi.getId(), "newDay");
							return "Agree authorize";
						}
					}
					
				}else{ 
					return "Reject authorize";
				}
			}else{
				return "No AuthorizedAPIInfo";
			}

		} else {
			return "Error message";
		}

	}

}
