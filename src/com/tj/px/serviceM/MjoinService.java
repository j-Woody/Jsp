package com.tj.px.serviceM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.MemberDao;
import com.tj.px.service.Service;

public class MjoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mEmail = request.getParameter("mEmail");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.join(mEmail, mName, mPw);
		if(result != dao.FAIL) {
			request.setAttribute("mEmail", mEmail);
			request.setAttribute("Msg", "회원가입 성공");
			
		}else {
			request.setAttribute("joinMsg", "중복된 아이디가 있습니다.");
		}
	}

}
