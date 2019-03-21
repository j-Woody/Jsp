package com.tj.px.serviceS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.SetPayDao;
import com.tj.px.service.Service;

public class SetPayService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		String pName = request.getParameter("pName");
		int pay = Integer.parseInt(request.getParameter("pay"));
		String pexp = request.getParameter("pexp");
		
		SetPayDao dao = SetPayDao.getInstance();
		int result = dao.setCount(pNo);
		if(result<3) {
		result = dao.setPay(pNo, pName, pay, pexp);
			if(result != dao.FAIL) {
				request.setAttribute("Msg", "등록성공");
			}
		}else {
			request.setAttribute("errorMsg", "등록실패");
		}
		
	}

}
