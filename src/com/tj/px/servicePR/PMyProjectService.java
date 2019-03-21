package com.tj.px.servicePR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.ProjectDao;
import com.tj.px.dto.ProjectDto;
import com.tj.px.service.Service;

public class PMyProjectService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProjectDao dao = ProjectDao.getInstance();
		HttpSession session = request.getSession();
		String mEmail =(String)session.getAttribute("mEmail"); 
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=20, BLOCKSIZE=5;
		int start = (currentPage-1) * PAGESIZE +1;
		int end   = start + PAGESIZE -1;
		
		ArrayList<ProjectDto> dto = dao.myProject(mEmail, start, end);
		
		int totCnt = dao.myCount(mEmail); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		
		request.setAttribute("list", dto);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); 
		request.setAttribute("pageNum", currentPage);
	}

}
