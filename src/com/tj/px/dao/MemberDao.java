package com.tj.px.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.px.dto.MemberDto;

public class MemberDao {
	private static MemberDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	public static final int BAN = -1;
	private MemberDao() {
	
	}
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
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
	//TODO:chkEmail
	public int chkEmail(String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEMBER WHERE MEMAIL=?";
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
	//TODO:join
	public int join(String mEmail,String mName,String mPw) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (MEMAIL,MNAME,MPW) VALUES(?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setString(2, mName);
			pstmt.setString(3, mPw);
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
	//TODO:modify
	public int modify(String mName,String mLoc,String mImage,String mSelf,String mPhone1,String mPhone2,String mPhone3,String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MNAME = ?,MLOC= ?,MIMAGE = ?,MSELF= ?,MPHONE1= ?,"
				+ "MPHONE2= ?,MPHONE3= ? WHERE MEMAIL= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mName);
			pstmt.setString(2, mLoc);
			pstmt.setString(3, mImage);
			pstmt.setString(4, mSelf);
			pstmt.setString(5, mPhone1);
			pstmt.setString(6, mPhone2);
			pstmt.setString(7, mPhone3);
			pstmt.setString(8, mEmail);
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
	//TODO:login
	public int login(String mEmail,String mPw) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MEMAIL = ? AND MPW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setString(2, mPw);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				int ban = rs.getInt("mBan");
				if(ban == 1) {
					result = BAN;
				}else {
					result = OK;
				}
			}
		} catch (Exception e) {
			System.out.println("login"+e.getMessage());
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//TODO: selectMember
	public MemberDto selectMember(String mEmail) {
			MemberDto dto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM MEMBER WHERE MEMAIL=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mEmail);
				pstmt.executeUpdate();
				rs= pstmt.executeQuery();
				if(rs.next()) {
					String mImage= rs.getString("mImage");
					String mName= rs.getString("mName");
					String mPw= rs.getString("mPw");
					Date mJdate= rs.getDate("mJdate");
					String mLoc= rs.getString("mLoc");
					String mSelf= rs.getString("mSelf");
					String mPhone1= rs.getString("mPhone1");
					String mPhone2= rs.getString("mPhone2");
					String mPhone3= rs.getString("mPhone3");
					int mAccess = rs.getInt("mAccess");
					int mBan = rs.getInt("mBan");
					dto = new MemberDto(mEmail, mImage, mName, mPw, mJdate, mLoc, mSelf, mPhone1, mPhone2, mPhone3, mAccess,mBan);
		
					
				}
			} catch (Exception e) {
				System.out.println("??selectMember"+e.getMessage());
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
	//TODO:getAllMember
	public ArrayList<MemberDto> getAllMember(int Start,int end) {
			ArrayList<MemberDto> dtos = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM RN, A.* FROM "
					+ "(SELECT * FROM MEMBER ORDER BY MNAME)A) "
					+ "WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Start);
				pstmt.setInt(2, end);
				pstmt.executeUpdate();
				rs= pstmt.executeQuery();
				if(rs.next()) {
					String mEmail = rs.getString("mEmail");
					String mImage= rs.getString("mImage");
					String mName= rs.getString("mName");
					String mPw= rs.getString("mPw");
					Date mJdate= rs.getDate("mJdate");
					String mLoc= rs.getString("mLoc");
					String mSelf= rs.getString("mSelf");
					String mPhone1= rs.getString("mPhone1");
					String mPhone2= rs.getString("mPhone2");
					String mPhone3= rs.getString("mPhone3");
					int mAccess = rs.getInt("mAccess");
					int mBan = rs.getInt("mBan");
					dtos.add(new MemberDto(mEmail, mImage, mName, mPw, mJdate, mLoc, mSelf, mPhone1, mPhone2, mPhone3, mAccess,mBan));
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
			
			return dtos;
	}
	//TODO:findMember
	public ArrayList<MemberDto> findMember(String insert,int Start,int end) {
		ArrayList<MemberDto> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM RN, A.* FROM "
				+ "(SELECT * FROM MEMBER ORDER BY MNAME)A) "
				+ "WHERE MEMAIL LIKE '%'||?||'%' OR MNAME LIKE'%'||?||'%' AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insert);
			pstmt.setString(2, insert);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String mEmail = rs.getString("mEmail");
				String mImage= rs.getString("mImage");
				String mName= rs.getString("mName");
				String mPw= rs.getString("mPw");
				Date mJdate= rs.getDate("mJdate");
				String mLoc= rs.getString("mLoc");
				String mSelf= rs.getString("mSelf");
				String mPhone1= rs.getString("mPhone1");
				String mPhone2= rs.getString("mPhone2");
				String mPhone3= rs.getString("mPhone3");
				int mAccess = rs.getInt("mAccess");
				int mBan = rs.getInt("mBan");
				dtos.add(new MemberDto(mEmail, mImage, mName, mPw, mJdate, mLoc, mSelf, mPhone1, mPhone2, mPhone3, mAccess,mBan));
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
		
		return dtos;
}
	
}
