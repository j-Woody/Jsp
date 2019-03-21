package com.tj.px.serviceB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.BoardDao;
import com.tj.px.service.Service;

public class BdeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		BoardDao dao = BoardDao.getInstance();
		int result = dao.deleteBoard(bNo);
		if(result != dao.FAIL) {
			request.setAttribute("Msg", "삭제완료");
			request.setAttribute("pageNum", pageNum);
		}else {
			request.setAttribute("errorMsg", "삭제실패");
		}
	}

}
