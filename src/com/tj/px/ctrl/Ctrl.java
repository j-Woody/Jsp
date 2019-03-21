package com.tj.px.ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.service.LogoutService;
import com.tj.px.service.Service;
import com.tj.px.serviceB.BInsertService;
import com.tj.px.serviceB.BViewService;
import com.tj.px.serviceB.BdeleteService;
import com.tj.px.serviceB.BlistService;
import com.tj.px.serviceB.BmodifyService;
import com.tj.px.serviceB.BmodifyViewService;
import com.tj.px.serviceB.BreplyService;
import com.tj.px.serviceB.BreplyViewService;
import com.tj.px.serviceB.BsearchService;
import com.tj.px.serviceM.MjoinService;
import com.tj.px.serviceM.MloginService;
import com.tj.px.serviceM.MmEmailChkService;
import com.tj.px.serviceM.MmodifyService;
import com.tj.px.serviceP.PaymentModifyService;
import com.tj.px.serviceP.PaymentService;
import com.tj.px.servicePR.PAllViewService;
import com.tj.px.servicePR.PInsertService;
import com.tj.px.servicePR.PMyProjectService;
import com.tj.px.servicePR.PwhooneService;
import com.tj.px.servicePR.PwhooneViewService;
import com.tj.px.servicePR.WhoOneDeleteService;
import com.tj.px.servicePR.PlistViewService;
import com.tj.px.servicePR.PmyWhoOneService;
import com.tj.px.servicePR.PselectViewService;
import com.tj.px.servicePR.PwhooneModifyService;
import com.tj.px.serviceS.SetPayService;

@WebServlet("*.do")
public class Ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ctrl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		actionDo(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request,response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service;
		String viewPage = "";
		if(command.equals("/loginView.do")) {// 로그인뷰
				viewPage ="member/login.jsp";
		}else if(command.equals("/login.do")) {//로그인
				service = new MloginService();
				service.execute(request, response);
				viewPage = "member/login.jsp";
		}else if(command.equals("/joinView.do")) {//회원가입뷰
				viewPage="member/join.jsp";
		}else if(command.equals("/join.do")) {//회원가입
				service = new MjoinService();
				service.execute(request, response);
				viewPage = "loginView.do";
		}else if(command.equals("/eMailChk.do")) {//이메일 중복여부 체크
			service = new MmEmailChkService();
			service.execute(request, response);
			viewPage = "member/mEmailChk.jsp";
		}else if(command.equals("/logout.do")) {//로그아웃
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/main.do")) {//메인뷰
			viewPage = "main/main.jsp";
		}else if(command.equals("/paymentView.do")) {//결제정보뷰
			viewPage = "member/payment.jsp";
		}else if(command.equals("/payment.do")) {//결제정보저장
			service = new PaymentService();
			service.execute(request, response);
			viewPage = "member/payment.jsp";
		}else if(command.equals("/pModifyView.do")) {//결제정보수정뷰
			viewPage = "member/paymentModify.jsp";
		}else if(command.equals("/pModify.do")) {//결제정보수정
			service = new PaymentModifyService();
			service.execute(request, response);
			viewPage = "member/paymentModify.jsp";
		}else if(command.equals("/mModifyView.do")) {//회원정보수정
			viewPage = "member/mModify.jsp";
		}else if(command.equals("/mModify.do")) {//회원정보수정
			service = new MmodifyService();
			service.execute(request, response);
			viewPage = "member/mModify.jsp";
		}
		/***********************project***************************/
		else if(command.equals("/pInsertView.do")) {//프로젝트 작성 뷰
			viewPage = "project/pInsertView.jsp";
		}else if(command.equals("/pInsert.do")) {//프로젝트 작성
			service = new PInsertService();
			service.execute(request, response);
			viewPage = "pInsertView.do";
		}else if(command.equals("/pListView.do")) {//프로젝트 리스트
			service = new PlistViewService();
			service.execute(request, response);
			viewPage = "project/getTop.jsp";
		}else if(command.equals("/pSeleteView.do")) {//프로젝트 보기
			service = new PselectViewService();
			service.execute(request, response);
			viewPage = "project/projectView.jsp";
		}else if(command.equals("/pWHoView.do")) {//프로젝트 후원뷰
			service = new PwhooneViewService();
			service.execute(request, response);
			viewPage = "project/whoone.jsp";
		}else if(command.equals("/pWHo.do")) {//프로젝트 후원 
			service = new PwhooneService();
			service.execute(request, response);
			viewPage = "pSeleteView.do";
		}else if(command.equals("/pMyProject.do")) {//내 프로젝트
			service = new PMyProjectService();
			service.execute(request, response);
			viewPage = "project/getTop.jsp";
		}else if(command.equals("/pWHoModifyView.do")) {//프로젝트 수정후원뷰
			service = new PwhooneViewService();
			service.execute(request, response);
			viewPage = "project/whooneM.jsp";
		}else if(command.equals("/pWHoModify.do")) {//프로젝트 후원 
			service = new PwhooneModifyService();
			service.execute(request, response);
			viewPage = "pSeleteView.do";
		}else if(command.equals("/pMyWhoOne.do")) {//내 후원목록
			service = new PmyWhoOneService();
			service.execute(request, response);
			viewPage = "project/getWhoOne.jsp";
		}else if(command.equals("/pAllView.do")) {//프로젝트 전체보기
			service = new PAllViewService();
			service.execute(request, response);
			viewPage = "project/projectAllView.jsp";
		}
		/***********************board***************************/
		else if(command.equals("/bInsertView.do")) {//유저게시판 글쓰기 뷰
			viewPage = "board/bInsert.jsp";
		}else if(command.equals("/bInsert.do")){//유저게시판 글쓰기
			service = new BInsertService();
			service.execute(request, response);
			viewPage = "bInsertView.do";
		}else if(command.equals("/bList.do")){//유저게시판 전체보기 페이징처리
			service = new BlistService();
			service.execute(request, response);
			viewPage = "board/bList.jsp";
		}else if(command.equals("/bView.do")){//유저게시판 상세보기
			service = new BViewService();
			service.execute(request, response);
			viewPage = "board/bView.jsp";
		}else if(command.equals("/bDelete.do")){//유저게시판 글삭제
			service = new BdeleteService();
			service.execute(request, response);
			viewPage = "bList.do";
		}else if(command.equals("/bModifyView.do")){//게시글 수정뷰
			service = new BmodifyViewService();
			service.execute(request, response);
			viewPage = "board/bModify.jsp";
		}else if(command.equals("/bModify.do")){//게시글 수정
			service = new BmodifyService();
			service.execute(request, response);
			viewPage = "bList.do";
		}else if(command.equals("/bReplyView.do")){//리플뷰
			service = new BreplyViewService();
			service.execute(request, response);
			viewPage = "board/bReplyInsert.jsp";
		}else if(command.equals("/bReply.do")){//리플작성
			service = new BreplyService();
			service.execute(request, response);
			viewPage = "bList.do";
		}else if(command.equals("/bSeach.do")){//검색
			service = new BsearchService();
			service.execute(request, response);
			viewPage = "board/bList.jsp";
		}
	 /************************************************************/
		else if(command.equals("/setPay.do")){//후원금 지정
			service = new SetPayService();
			service.execute(request, response);
			viewPage = "pSeleteView.do";
		}else if(command.equals("/whoOneDelete.do")){//후원금 삭제
			service = new WhoOneDeleteService();
			service.execute(request, response);
			viewPage = "project/myWhoOne.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}


}
