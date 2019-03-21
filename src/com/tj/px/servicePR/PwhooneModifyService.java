package com.tj.px.servicePR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.GetProjectDao;
import com.tj.px.dao.ProjectDao;
import com.tj.px.service.Service;

public class PwhooneModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProjectDao dao = ProjectDao.getInstance();
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		HttpSession session = request.getSession();
		String mEmail = (String)session.getAttribute("mEmail");
		int pay = Integer.parseInt(request.getParameter("pay"));
		GetProjectDao gDao = GetProjectDao.getInstance();
		int result = gDao.update(mEmail, pNo, pay);
		if(result != gDao.FAIL) {
			request.setAttribute("Msg", "후원변경완료");
		}else {
			request.setAttribute("errorMsg", "후원변경실패");
		}
		
	}

}
