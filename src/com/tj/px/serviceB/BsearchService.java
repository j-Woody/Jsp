package com.tj.px.serviceB;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.BoardDao;
import com.tj.px.dao.MemberDao;
import com.tj.px.dto.BoardDto;
import com.tj.px.service.Service;

public class BsearchService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String select = request.getParameter("select");
		String key = request.getParameter("key");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=10, BLOCKSIZE=5;
		int start = (currentPage-1) * PAGESIZE +1;
		int end   = start + PAGESIZE -1;
		
		ArrayList<BoardDto> dto = new ArrayList<BoardDto>();
		BoardDao dao = BoardDao.getInstance();
		int result = dao.getSearchCnt(key, select);
		if(result != dao.FAIL) {
			
			dto = dao.seach(select, start, end, key);
			if(dto != null) {
				request.setAttribute("bDto", dto);
			}else {
				request.setAttribute("errorMsg", "검색실패");
			}
		}else {
			request.setAttribute("errorMsg", "검색할 내용이 없습니다.");
		}
		
		
		int pageCnt = (int)Math.ceil((double)result/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", result); 
		request.setAttribute("pageNum", currentPage);
	}

}
