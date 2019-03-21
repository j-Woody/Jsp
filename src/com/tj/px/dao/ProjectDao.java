package com.tj.px.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.px.dto.GetWhoOneDto;
import com.tj.px.dto.ProjectDto;

public class ProjectDao {
	private static ProjectDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	private ProjectDao() {
	
	}
	public static ProjectDao getInstance() {
		if(instance == null) {
			instance = new ProjectDao();
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
	//TODO: 프로젝트 등록
	public int insertProject(String pTitle,String pDate,String mEmail,int pPay,String pContent, String pImage,String pCategory,String pAccount,String pBankName,String pAcName) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PROJECT (PNO,PTITLE,PDATE,MEMAIL,PPAY,PCONTENT,PIMAGE,PCATEGORY,PACCOUNT,PBANKNAME,PACNAME)VALUES(PROJECT_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pTitle);
			pstmt.setString(2, pDate);
			pstmt.setString(3, mEmail);
			pstmt.setInt(4, pPay);
			pstmt.setString(5, pContent);
			pstmt.setString(6, pImage);
			pstmt.setString(7, pCategory);
			pstmt.setString(8, pAccount);
			pstmt.setString(9, pBankName);
			pstmt.setString(10, pAcName);
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
	//TODO:내 프로젝트 보기
	public ArrayList<ProjectDto> myProject(String mEmail,int start,int end){
	ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PCNT)A)WHERE MEMAIL='?' AND RN BETWEEN ? AND ?";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mEmail);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		pstmt.executeUpdate();
		rs= pstmt.executeQuery();
		if(rs.next()) {
			do {
			int pNo= rs.getInt("pNo");
			String pTitle= rs.getString("pTitle");
			Date pDate= rs.getDate("pDate");
			int pPay = rs.getInt("pPay");
			int pNow = rs.getInt("pNow");
			int pCnt = rs.getInt("pCnt");
			String pContent = rs.getString("pContent");
			String pImage = rs.getString("pImage");
			String pCategory = rs.getString("pCategory");
			String pAccount = rs.getString("pAccount");
			String pBankName = rs.getString("pBankName");
			String pAcName = rs.getString("pAcName");
			dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
			}while(rs.next());
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
	return dto;
}
	public ArrayList<GetWhoOneDto> myWhoOne(String mEmail,int start,int end){
		ArrayList<GetWhoOneDto> dto = new ArrayList<GetWhoOneDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT P.*,A.GNO,A.PAY FROM PROJECT P,(SELECT ROWNUM RN, G.* FROM MEMBER M , GETPROJECT G WHERE M.MEMAIL = G.MEMAIL AND G.MEMAIL=? ORDER BY PNO) A WHERE A.PNO = P.PNO AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String mEmail2=rs.getString("mEmail");
				String pTitle= rs.getString("pTitle");
				Date pDate= rs.getDate("pDate");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pImage = rs.getString("pImage");
				int gNo = rs.getInt("gNo");
				int pay = rs.getInt("pay");
				dto.add(new GetWhoOneDto(pNo, mEmail2, pTitle, pDate, pPay, pNow, pCnt, pImage, gNo, pay));
				}while(rs.next());
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
		return dto;
	}
	//TODO:프로젝트 전체보기(paging)
	public ArrayList<ProjectDto> allViewProject(int start,int end){
		ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PNO)A)WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String pTitle= rs.getString("pTitle");
				Date pDate= rs.getDate("pDate");
				String mEmail = rs.getString("mEmail");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				String pCategory = rs.getString("pCategory");
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	//TODO:카테고리별 보기
	public ArrayList<ProjectDto> categoryView(String pCategory,int start,int end){
		ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PCNT)A)WHERE PCATEGORY=? AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String pTitle= rs.getString("pTitle");
				String mEmail = rs.getString("mEmail");
				Date pDate= rs.getDate("pDate");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	//TODO:검색하기
	public ArrayList<ProjectDto> seach(String seach,int start,int end){
		ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PTITLE)A)WHERE MEMAIL LIKE '%'||?||'%' OR PTITLE LIKE '%'||?||'%' AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seach);
			pstmt.setString(2, seach);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String pTitle= rs.getString("pTitle");
				String mEmail = rs.getString("mEmail");
				Date pDate= rs.getDate("pDate");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				String pCategory = rs.getString("pCategory");
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	//TODO:후원 인원 증가
	public void cntUp(int pNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PROJECT SET PCNT=PCNT+1 WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.executeUpdate();
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
	}
	//TODO:후원시 후원금증가
	public int payUp(int pay, int pNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PROJECT SET PNOW=PNOW+? WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay);
			pstmt.setInt(2, pNo);
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
	//TODO: 프로젝트 보기(상세페이지)
	public ProjectDto getProject(int pNo){
		ProjectDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PROJECT WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String pTitle= rs.getString("pTitle");
				Date pDate= rs.getDate("pDate");
				String mEmail = rs.getString("mEmail");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				String pCategory = rs.getString("pCategory");
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto = new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName);
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	public ArrayList<ProjectDto> banner(int start,int end){
		ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PCNT DESC)A)WHERE PDATE > SYSDATE AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String pTitle= rs.getString("pTitle");
				Date pDate= rs.getDate("pDate");
				String mEmail = rs.getString("mEmail");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				String pCategory = rs.getString("pCategory");
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	public ArrayList<ProjectDto> hitView(int start,int end){
		ArrayList<ProjectDto> dto = new ArrayList<ProjectDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PROJECT ORDER BY PCNT)A)WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int pNo= rs.getInt("pNo");
				String pTitle= rs.getString("pTitle");
				String mEmail = rs.getString("mEmail");
				Date pDate= rs.getDate("pDate");
				int pPay = rs.getInt("pPay");
				int pNow = rs.getInt("pNow");
				int pCnt = rs.getInt("pCnt");
				String pContent = rs.getString("pContent");
				String pImage = rs.getString("pImage");
				String pCategory =rs.getString("pCategory");
				String pAccount = rs.getString("pAccount");
				String pBankName = rs.getString("pBankName");
				String pAcName = rs.getString("pAcName");
				dto.add(new ProjectDto(pNo, pTitle, mEmail, pDate, pPay, pNow, pCnt, pContent, pImage, pCategory, pAccount, pBankName, pAcName));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMember"+e.getMessage());
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
	//TODO:cnt
	public int count() {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PROJECT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
	public int myCount(String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PROJECT WHERE MEMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
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
	
	public int whoOneCount(String mEmail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PROJECT P,(SELECT G.PNO FROM MEMBER M , GETPROJECT G WHERE M.MEMAIL = G.MEMAIL AND G.MEMAIL=? ORDER BY PNO) A WHERE A.PNO = P.PNO";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
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
}
