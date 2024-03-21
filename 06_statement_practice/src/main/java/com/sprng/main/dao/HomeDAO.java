package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDAO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	Connection conn = null;
	
	public HomeDAO() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MariaDB");
			 conn = ds.getConnection();
			logger.info("접속 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean stmt() {
		boolean result = false;
		int row = -1;
		//1. 쿼리문 작성
		String sql = "create table member("
				+ "	ID varchar(50) primary key"
				+ "	,PW varchar(100)"
				+ "	,NAME varchar(50)"
				+ "	,AGE int(4)"
				+ "	,GENDER varchar(4)"
				+ "	,EMAIL varchar(100)"
				+ ")";
		try {
			//2. statement 객체 생성
			Statement st = conn.createStatement();
			//3. 쿼리문 실행
			row = st.executeUpdate(sql);
			//4. 자원반납
			st.close();
			conn.close();
			
			if (row != -1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public boolean insert(String id, String pw, String name, String age, String gender, String email) {
		
		int row = -1;
		
		
		//1. 쿼리문 작성
		String spl = "insert into member(id,pw,name,age,gender,email) values(?,?,?,?,?,?) ";

		try {
			conn.setAutoCommit(false);
			//2. Statement 객체 생성
			PreparedStatement ps = conn.prepareStatement(spl);
			//2-1. ? 값 넣어주기
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, Integer.parseInt(age));
			ps.setString(5, gender);
			ps.setString(6, email);
			
			//3. 쿼리문 실행
			row = ps.executeUpdate();
			row = ps.executeUpdate();
			
			conn.commit();
			
			//4. 자원 반납
			ps.close();
			conn.close();
			
			if (row != -1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

}
