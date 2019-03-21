package com.tj.px.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

import com.tj.px.dto.MemberDto;
import com.tj.px.dto.PaymentDto;

public class PaymentDao {
	private static PaymentDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	private PaymentDao() {
		// TODO Auto-generated constructor stub
	}
	public static PaymentDao getInstance() {
		if(instance == null) {
			instance = new PaymentDao();
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
	//TODO:insert
	public int insertCard(String pcNum,String pMonth,String pYear,String pBirth,String pPw,String pPhone1,String pPhone2,String pPhone3,String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PAYMENT VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcNum);
			pstmt.setString(2, pMonth);
			pstmt.setString(3, pYear);
			pstmt.setString(4, pBirth);
			pstmt.setString(5, pPw);
			pstmt.setString(6, pPhone1);
			pstmt.setString(7, pPhone2);
			pstmt.setString(8, pPhone3);
			pstmt.setString(9, mEmail);
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
	public int chkCard(String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM PAYMENT WHERE MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
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
	//TODO: update
	public int updateCard(String pcNum,String pMonth,String pYear,String pBirth,String pPw,String pPhone1,String pPhone2,String pPhone3,String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PAYMENT SET PCNUM=?,PMONTH=?,PYEAR=?,PBIRTH=?,PPW=?,PPHONE1=?,PPHONE2=?,PPHONE3=? WHERE MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcNum);
			pstmt.setString(2, pMonth);
			pstmt.setString(3, pYear);
			pstmt.setString(4, pBirth);
			pstmt.setString(5, pPw);
			pstmt.setString(6, pPhone1);
			pstmt.setString(7, pPhone2);
			pstmt.setString(8, pPhone3);
			pstmt.setString(9, mEmail);
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
	//TODO: chkCardNum
	public int chkCardNum(String pcNum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PAYMENT WHERE PCNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcNum);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//TODO: get
	public PaymentDto select(String mEmail) {
		PaymentDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT B.* FROM MEMBER A, PAYMENT B WHERE A.MEMAIL=B.MEMAIL AND A.MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String pcNum = rs.getString("pcNum");
				String pMonth = rs.getString("pMonth");
				String pYear = rs.getString("pYear");
				String pBirth = rs.getString("pBirth");
				String pPw = rs.getString("pPw");
				String pPhone1 = rs.getString("pPhone1");
				String pPhone2 = rs.getString("pPhone2");
				String pPhone3 = rs.getString("pPhone3");
				dto = new PaymentDto(pcNum, pMonth, pYear, pBirth, pPw, pPhone1, pPhone2, pPhone3, mEmail);
	
				
			}
		} catch (Exception e) {
			System.out.println("PDTO"+e.getMessage());
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
}
