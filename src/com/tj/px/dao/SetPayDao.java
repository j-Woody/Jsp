package com.tj.px.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;
import com.tj.px.dto.ProjectDto;
import com.tj.px.dto.SetPayDto;

public class SetPayDao {
	private static SetPayDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	private SetPayDao() {
		// TODO Auto-generated constructor stub
	}
	public static SetPayDao getInstance() {
		if(instance == null) {
			instance = new SetPayDao();
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
	//TODO:insert(set)
	public int setPay(int pNo,String pName,int pay,String pexp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SETPAY VALUES(SETPAY_SEQ.NEXTVAL,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.setString(2, pName);
			pstmt.setInt(3, pay);
			pstmt.setString(4, pexp);
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
	//TODO:count
	public int setCount(int pNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM SETPAY WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			 pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//TODO:get
	public ArrayList<SetPayDto> getSetPay(int pNo){
		ArrayList<SetPayDto> dto = new ArrayList<SetPayDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SETPAY WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int spNo = rs.getInt("spNo");
				String pName = rs.getString("pName");
				int pay = rs.getInt("pay");
				String pexp = rs.getString("pexp");
				dto.add(new SetPayDto(spNo, spNo, pName, pay, pexp));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("sdto"+e.getMessage());
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
