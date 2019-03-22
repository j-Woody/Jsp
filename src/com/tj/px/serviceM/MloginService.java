package com.tj.px.serviceM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.AdminDao;
import com.tj.px.dao.MemberDao;
import com.tj.px.dao.PaymentDao;
import com.tj.px.dto.MemberDto;
import com.tj.px.dto.PaymentDto;
import com.tj.px.service.Service;

public class MloginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mEmail = request.getParameter("mEmail");
		String mPw = request.getParameter("mPw");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = null;
		PaymentDao pdao = PaymentDao.getInstance();
		PaymentDto pdto = null;
		HttpSession session= request.getSession();
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.chkAdmin(mEmail, mPw);
		if(result == aDao.FAIL) {
		result = dao.login(mEmail, mPw);
		pdto = pdao.select(mEmail);
			if(pdto != null) {
				session.setAttribute("pcDto", pdto);
				System.out.println("pcdto발생");
			}
		if(result == dao.OK) {
			dto = dao.selectMember(mEmail);
			session.setAttribute("mDto", dto);
			session.setAttribute("mEmail", mEmail);
			
			
		}else if(result==dao.FAIL) {
			request.setAttribute("errorMsg", "아이디와 비밀번호를 확인해주세요.");
		}
		}else {
			session.setAttribute("admin", aDao);
			System.out.println("관리자 로그인");
		}
	}

}
