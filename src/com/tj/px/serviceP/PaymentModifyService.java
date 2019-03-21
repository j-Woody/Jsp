package com.tj.px.serviceP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.PaymentDao;
import com.tj.px.service.Service;

public class PaymentModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PaymentDao dao = PaymentDao.getInstance();
		String pc1 = request.getParameter("pc1");
		String pc2 = request.getParameter("pc2");
		String pc3 = request.getParameter("pc3");
		String pc4 = request.getParameter("pc4");
		String pcNum = pc1+"-"+pc2+"-"+pc3+"-"+pc4;
		int result = dao.chkCardNum(pcNum);
	
		if(result == dao.FAIL) {
			String pMonth = request.getParameter("pMonth");
			String pYear = request.getParameter("pYear");
			String pBirth = request.getParameter("pBirth");
			String pPw = request.getParameter("pPw");
			String pPhone1 = request.getParameter("pPhone1");
			String pPhone2 = request.getParameter("pPhone2");
			String pPhone3 = request.getParameter("pPhone3");
			HttpSession session = request.getSession();
			String mEmail = (String)session.getAttribute("mEmail");
			
			result = dao.updateCard(pcNum, pMonth, pYear, pBirth, pPw, pPhone1, pPhone2, pPhone3, mEmail);
			if(result == dao.FAIL) {
				request.setAttribute("errorMsg", "카드수정 실패");
			}else {
				request.setAttribute("Msg", "카드수정 성공");
			}
		}else {
			request.setAttribute("errorMsg", "이미 등록된 카드번호입니다.");
		}
	}	

}
