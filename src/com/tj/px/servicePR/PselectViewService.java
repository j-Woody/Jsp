package com.tj.px.servicePR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.px.dao.GetProjectDao;
import com.tj.px.dao.ProjectDao;
import com.tj.px.dao.SetPayDao;
import com.tj.px.dto.GetProjectDto;
import com.tj.px.dto.ProjectDto;
import com.tj.px.dto.SetPayDto;
import com.tj.px.service.Service;

public class PselectViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		HttpSession session = request.getSession();
		ProjectDao dao = ProjectDao.getInstance();
		ProjectDto pDto = dao.getProject(pNo);
		SetPayDao sdao = SetPayDao.getInstance();
		ArrayList<SetPayDto> sdto = null;
		String mEmail = (String)session.getAttribute("mEmail");
		GetProjectDao gDao = GetProjectDao.getInstance();
		GetProjectDto gDto = gDao.get(mEmail, pNo);
		sdto = sdao.getSetPay(pNo);
		if(pDto != null) {
			request.setAttribute("gDto", gDto);
			request.setAttribute("sDto", sdto);
			request.setAttribute("pDto",pDto);
		}else {
			request.setAttribute("errorMsg", "프로젝트 불러오기 실패");
			
		}
	
	}

}
