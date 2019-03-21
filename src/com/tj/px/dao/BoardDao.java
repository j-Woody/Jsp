package com.tj.px.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.px.dto.BoardDto;

public class BoardDao {
	private static BoardDao instance;
	public static final int OK = 1;
	public static final int FAIL = 0;
	private BoardDao() {
		// TODO Auto-generated constructor stub
	}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
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
	//TODO:본문 글쓰기
	public int insertBoard(String mEmail,String bTitle,String bContent,String bImage,String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (BNO,MEMAIL,BTITLE,BCONTENT,BIMAGE,BGROUP,BIP)VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,BOARD_SEQ.CURRVAL,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bImage);
			pstmt.setString(5, bIp);
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
	//TODO:전체 글수
	public int getCount() {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) FROM BOARD";
		ResultSet rs = null;
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
				if(rs !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//TODO:전체 출력
	public ArrayList<BoardDto> allViewBoard(int start,int end){
		ArrayList<BoardDto> dto = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "(SELECT *FROM BOARD ORDER BY BGROUP DESC,BSTEP)A) "
				+ "WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int bNo= rs.getInt("bNo");
				String mEmail= rs.getString("mEmail");
				String bTitle= rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bImage = rs.getString("bImage");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				Date bDate= rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				String bIp = rs.getString("bIp");
				dto.add(new BoardDto(bNo, mEmail, bTitle, bContent, bImage, bGroup, bStep, bIndent, bDate, bHit, bIp));
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
	//TODO:하나 불러오기
	public BoardDto getBoard(int bNo){
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String mEmail= rs.getString("mEmail");
				String bTitle= rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bImage = rs.getString("bImage");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				Date bDate= rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				String bIp = rs.getString("bIp");
				dto = new BoardDto(bNo, mEmail, bTitle, bContent, bImage, bGroup, bStep, bIndent, bDate, bHit, bIp);
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
	//TODO:수정
	public int updateBoard(String bTitle,String bContent,String bImage,int bNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BTITLE = ?,BCONTENT=?,BIMAGE=? WHERE BNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bImage);
			pstmt.setInt(4, bNo);
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
	//TODO: 조회수 증가
	public void cntUp(int bNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
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
	//TODO:글삭제
	public int deleteBoard(int bNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
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
	//TODO:댓글 bstep+1시키기
	private void bStepUp(int bgroup, int bstep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BSTEP = BSTEP+1 WHERE BGROUP = ? AND BSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
	}
	// 답변글 쓰기
	public int reply(String mEmail, String bTitle, String bContent,int bGroup, String bIp,int bStep, int bIndent) {
		bStepUp(bGroup, bStep); //이전 답글 bStep +1
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD "
				+ "(BNO,MEMAIL,BTITLE,BCONTENT,BGROUP,BIP,BSTEP,BINDENT)"
				+ "VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bGroup);
			pstmt.setString(5, bIp);
			pstmt.setInt(6, bStep+1);
			pstmt.setInt(7, bIndent+1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//TODO:검색기능
	public ArrayList<BoardDto> seach(String select,int start,int end,String key){
		ArrayList<BoardDto> dto = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(select.equals("제목")) {
			sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BTITLE LIKE '%'||?||'%' ORDER BY BGROUP DESC,BSTEP)A)WHERE RN BETWEEN ? AND ?";
		}else {
			sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE MEMAIL LIKE '%'||?||'%' ORDER BY BGROUP DESC,BSTEP)A)WHERE RN BETWEEN ? AND ?";
		}
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt.executeUpdate();
			rs= pstmt.executeQuery();
			if(rs.next()) {
				do {
				int bNo= rs.getInt("bNo");
				String mEmail= rs.getString("mEmail");
				String bTitle= rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bImage = rs.getString("bImage");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				Date bDate= rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				String bIp = rs.getString("bIp");
				dto.add(new BoardDto(bNo, mEmail, bTitle, bContent, bImage, bGroup, bStep, bIndent, bDate, bHit, bIp));
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
	//TODO:seach cnt
	public int getSearchCnt(String key,String select) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="";
		if(select.equals("제목")) {
		 sql = "SELECT COUNT(*) FROM BOARD WHERE BTITLE LIKE '%'||?||'%'";
		}else {
			sql="SELECT COUNT(*) FROM BOARD WHERE MEMAIL LIKE '%'||?||'%'";
		}
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
}
