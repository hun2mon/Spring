package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDAO {

	private static final Logger logger = LoggerFactory.getLogger(HomeDAO.class);	
	
	public boolean dbConnect() {
		boolean result = false;
		
		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			//2.매니저 호출
			Class.forName("org.mariadb.jdbc.Driver");
			
			//3. 정보 전달
			Connection conn = DriverManager.getConnection(url, id, pw);
			
			// 4. 할일
			if (conn != null) {
				result = true;
			}
			
			//5. 반납
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//
		
		
		return result;
	}

	public boolean dbPool() {
		boolean result = false;
		
		try {
			// 1. context.xml 자바 객체로 변환
			Context ctx = new InitialContext();
			
			// 2. 
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MariaDB");
			
			// 3.
			Connection conn = ds.getConnection();
			
			if (conn != null) {
				result = true;
				conn.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
