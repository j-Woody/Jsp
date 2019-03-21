package com.tj.px.servicePR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.ProjectDao;
import com.tj.px.dto.ProjectDto;
import com.tj.px.service.Service;

public class PlistViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
	
		final int PAGESIZE=4;
		int currentPage = Integer.parseInt(pageNum);
		ProjectDao dao = ProjectDao.getInstance();
		int totCnt = dao.count(); // 글갯수
		System.out.println(totCnt);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		
		if(currentPage<=pageCnt) {			
			int startRow = (currentPage-1) * PAGESIZE +1;
			int endRow   = startRow + PAGESIZE -1;
			ArrayList<ProjectDto> list = dao.hitView(startRow, endRow);
			request.setAttribute("list", list);
			HttpSession session = request.getSession();
			request.setAttribute("pageNum", currentPage);
			request.setAttribute("pageCnt", pageCnt);
		}else {
			request.setAttribute("error", "over");
		}
	
	}
	

}
