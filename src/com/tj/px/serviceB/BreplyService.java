package com.tj.px.serviceB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.BoardDao;
import com.tj.px.service.Service;

public class BreplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int bGroup   = Integer.parseInt(request.getParameter("bGroup"));
		int bStep    = Integer.parseInt(request.getParameter("bStep"));
		int bIndent  = Integer.parseInt(request.getParameter("bIndent"));
		String mEmail = (String)session.getAttribute("mEmail");
		String bTitle= request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		String bIp = request.getRemoteAddr();
		BoardDao dao = BoardDao.getInstance();
		int result = dao.reply(mEmail, bTitle, bContent, bGroup, bIp, bStep, bIndent);
		if(result != dao.FAIL) {
			request.setAttribute("Msg", "답글 작성 성공");
		}else {
			request.setAttribute("errorMsg", "답글 작성 실패");
		}

	}

}
