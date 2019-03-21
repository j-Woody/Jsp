package com.tj.px.serviceP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.PaymentDao;
import com.tj.px.dto.MemberDto;
import com.tj.px.dto.PaymentDto;
import com.tj.px.service.Service;

public class PaymentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pc1 = request.getParameter("pc1");
		String pc2 = request.getParameter("pc2");
		String pc3 = request.getParameter("pc3");
		String pc4 = request.getParameter("pc4");
		String pcNum = pc1+"-"+pc2+"-"+pc3+"-"+pc4;
		String pMonth = request.getParameter("pMonth");
		String pYear = request.getParameter("pYear");
		String pBirth = request.getParameter("pBirth");
		String pPw = request.getParameter("pPw");
		String pPhone1 = request.getParameter("pPhone1");
	
		String pPhone2 = request.getParameter("pPhone2");
		String pPhone3 = request.getParameter("pPhone3");
		String mEmail = (String)session.getAttribute("mEmail");
		PaymentDao dao = PaymentDao.getInstance();
		PaymentDto dto;
		int result = dao.insertCard(pcNum, pMonth, pYear, pBirth, pPw, pPhone1, pPhone2, pPhone3, mEmail);
		if(result == dao.FAIL) {
			request.setAttribute("errorMsg", "카드등록 실패");
		}else {
			request.setAttribute("Msg", "카드등록 성공");
			dto = dao.select(mEmail);
			session.setAttribute("pDto", "pDto");
		}
	}
}
