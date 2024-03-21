package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dto.MemberDTO;

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

	public int join(String id, String pw, String name, String age, String gender, String email) {
		//1. 쿼리문 작성
		String sql = "insert into member(id, pw, name, age, gender, email) values (?,?,?,?,?,?)";
		int row = -1;
		try {
			conn.setAutoCommit(false);
			//2. Statement || PreparedStatement 객체 생성
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return row;
	}

	public boolean login(String id, String pw) {
		boolean success = false;
		
		//1. 쿼리문 준비
		String sql = "select id from member where id = ? and pw = ?";
		try {
			//2. 실행 객체 생성
			PreparedStatement ps = conn.prepareStatement(sql);
			//3. 실행
			ps.setString(1, id);
			ps.setString(2, pw);
			//4. 값 꺼내기
			ResultSet rs = ps.executeQuery();
			success = rs.next();
			//5. 자원반납
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	public List<MemberDTO> list() {
		//1. 쿼리문 준비
		String sql = "select id,name,age,email from member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			//2. 실행 객체 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			//3. 실행
			ResultSet rs = ps.executeQuery();
			
			//4. 데이터 가져오기
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				MemberDTO dto = new MemberDTO(id, name, age, email);
				list.add(dto);
			}
			//5. 자원 반납
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public MemberDTO detail(String id) {
		logger.info(id + "sadfas");
		//1. 쿼리문 작성
		String sql = "select id,pw,name,age,gender,email from member where id = ?";
		MemberDTO dto = null;
		try {
			//2. 객체 생성
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				String mem_id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				dto = new MemberDTO(mem_id, pw, name, age, gender, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;			
	}

	public void del(String id) {
		String msg = "다시 시도해 주세요";
		//1. 쿼리문 작성
		String sql = "delete from member where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}











