package com.tj.px.servicePR;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.SetPayDao;
import com.tj.px.dto.SetPayDto;
import com.tj.px.service.Service;

public class PwhooneViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		ArrayList<SetPayDto> sdto = null;
		SetPayDao sdao = SetPayDao.getInstance();
		sdto = sdao.getSetPay(pNo);
		if(sdto != null) {
			request.setAttribute("sDto", sdto);
			request.setAttribute("pNo", pNo);
		}else {
			request.setAttribute("errorMsg", "프로젝트 불러오기 실패");
		}
	}

}
