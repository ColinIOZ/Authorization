package com.big.authorization.dao.jdbcdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.big.authorization.po.User;
import com.big.authorization.util.DBConnUtil;
/** CopyRright (c) 2013: Biodiversity Informatics Group of IOZ, all right reserved
 * Project: authorization
 * Module ID:
 * Comments:
 * JDK version used: <JDK1.7>
 * Namespace: 
 * Author：LHH@BIG.IOZ
 * Create Date：2013-8-23
 * Modified By：
 * Modified Date:
 * Why & What is modified:
 * Version: 0.1.0
 * 
 */
public class JdbcBaseDao {

	/**
	 * 根据用户名查询用户信息
	 * @param name：参数
	 * @return：返回整个用户信息
	 */
	public static User getUserByName(String name){
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select * from user where user_name = ?");
		
		try {
			conn =  DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setObject(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return user;
	}
	/**
	 * 根据用户的email查询用户信息
	 * @param email：参数
	 * @return：返回整个用户信息
	 */
	public static User getUserByEmail(String email){
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select * from user where email = ?");
		
		try {
			conn =  DBConnUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setObject(1, email);
			rs = ps.executeQuery();
			while(rs.next()){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnUtil.closeAll(rs, ps, conn);
		}
		return user;
	}
}
