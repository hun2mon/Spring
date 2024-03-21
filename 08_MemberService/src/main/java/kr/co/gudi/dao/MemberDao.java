package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberDao {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	Connection conn = null;
	
	public MemberDao() {
		 
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean join(String id, String pw, String name, String age, String gender, String email) {
		boolean result = false;
		//1. 쿼리문 작성
		String sql = "insert into member(id, pw, name, age, gender, email) values (?,?,?,?,?,?)";
		int row = -1;
		try {
			conn.setAutoCommit(false);
			//2. statement 객체 생성
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, Integer.parseInt(age));
			ps.setString(5, gender);
			ps.setString(6, email);
			
			//3. 쿼리문 실행
			row = ps.executeUpdate();
			
			conn.commit();
			
			//4. 자원반납
			conn.close();
			ps.close();
			
			if (row != -1) {
				result = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return result;
	}



}
