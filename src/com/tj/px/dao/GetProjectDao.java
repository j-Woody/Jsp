package com.tj.px.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

import com.tj.px.dto.GetProjectDto;

public class GetProjectDao {
	private static GetProjectDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	private GetProjectDao() {
		// TODO Auto-generated constructor stub
	}
	public static GetProjectDao getInstance() {
		if(instance == null) {
			instance = new GetProjectDao();
		}
		return instance;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context ct = new InitialContext();
			DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public int insert(String mEmail,int pNo,int pay) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GETPROJECT VALUES(GETPROJECT_SEQ.NEXTVAL,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setInt(2, pNo);
			pstmt.setInt(3, pay);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	public GetProjectDto get(String mEmail,int pNo) {
		GetProjectDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM GETPROJECT WHERE PNO=? AND MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.setString(2, mEmail);
			
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int gNo = rs.getInt("gNo");
				int pay = rs.getInt("pay");
				dto = new GetProjectDto(gNo, mEmail, pNo, pay);
			}
		} catch (Exception e) {
			System.out.println(
					"gDto : "+e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return dto;
	}
	public int update(String mEmail,int pNo,int pay) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GETPROJECT SET PAY= ? WHERE PNO=? AND MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay);
			pstmt.setInt(2, pNo);
			pstmt.setString(3, mEmail);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	public int deleteWhoOne(int gNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GETPROJECT WHERE GNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
}
