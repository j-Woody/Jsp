package com.tj.px.serviceB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.BoardDao;
import com.tj.px.dto.BoardDto;
import com.tj.px.service.Service;

public class BmodifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum")); 
		BoardDao dao = BoardDao.getInstance();
		BoardDto dto = dao.getBoard(bNo);
		request.setAttribute("bDto", dto);
		request.setAttribute("pageNum", pageNum);
	}

}
