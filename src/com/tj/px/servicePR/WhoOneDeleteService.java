package com.tj.px.servicePR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.px.dao.GetProjectDao;
import com.tj.px.service.Service;

public class WhoOneDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("delete들어옴");
		GetProjectDao dao = GetProjectDao.getInstance();
		int gNo = Integer.parseInt(request.getParameter("gNo"));
		System.out.println(gNo);
		 dao.deleteWhoOne(gNo);
		
	}

}
