package com.tj.px.serviceM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.MemberDao;
import com.tj.px.service.Service;

public class MmEmailChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mEmail = request.getParameter("mEmail");
		MemberDao dao = MemberDao.getInstance();
		
		int result = dao.chkEmail(mEmail);
	
		if(result != dao.FAIL) {
			request.setAttribute("emailMsg", "이미 등록된 아이디입니다.");
		}else {
			request.setAttribute("emailMsg", "");
		}
	}

}
