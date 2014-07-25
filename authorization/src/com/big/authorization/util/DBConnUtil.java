package com.big.authorization.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
	private static String jdbcDriver = "";
	private static String jdbcUrl = "";
	private static String jdbcUser = "";
	private static String jdbcPwd = "";
	
	static{
		InputStream is = null;
		is = DBConnUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			jdbcDriver = p.getProperty("jdbcDriver");
			jdbcUrl = p.getProperty("jdbcUrl");
			jdbcUser = p.getProperty("jdbcUser");
			jdbcPwd = p.getProperty("jdbcPwd");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(DBConnUtil.getConnection());
	}
}
