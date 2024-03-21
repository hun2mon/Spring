package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainDAO {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Connection conn = null;
	
	public MainDAO() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			logger.info("connect : " + conn);
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	public void insert() {
		//1. 쿼리문 작성
		String sql = "insert into member(id,pw,name,age,gender,email)values(?,?,?,?,?,?)";
		try {
			// auto commit 종료
			conn.setAutoCommit(false);
			
			//2. Statement 객체 생성
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "tester3");
			ps.setString(2, "1234");
			ps.setString(3, "사용자");
			ps.setInt(4, 30);
			ps.setString(5, "여자");
			ps.setString(6, "tester@email.com");
			
			//3. executeQuery | executeUpdate
			int row = ps.executeUpdate();
			logger.info("영향 받은 데이서 수 : " + row);
			
			row = ps.executeUpdate();
			logger.info("영향 받은 데이서 수 : " + row);
			
			conn.commit(); // 성공 후 커밋;
			
			//4. 자원 반납
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
	}

}
