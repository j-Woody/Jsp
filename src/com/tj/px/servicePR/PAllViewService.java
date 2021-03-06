package com.tj.px.servicePR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.BoardDao;
import com.tj.px.dao.ProjectDao;
import com.tj.px.dto.BoardDto;
import com.tj.px.dto.ProjectDto;
import com.tj.px.service.Service;

public class PAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* System.out.println("bList.do 들어옴"); */
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=5, BLOCKSIZE=5;
		int start = (currentPage-1) * PAGESIZE +1;
		int end   = start + PAGESIZE -1;
		ProjectDao dao = ProjectDao.getInstance();
		ArrayList<ProjectDto> list = dao.allViewProject(start, end);
		request.setAttribute("list", list);
		int totCnt = dao.count(); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); 
		request.setAttribute("pageNum", currentPage);
	}

}
