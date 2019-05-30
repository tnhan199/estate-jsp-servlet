package com.trantrongnhan.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.service.IBuildingService;
import com.trantrongnhan.service.impl.BuildingService;
import com.trantrongnhan.sorting.Sorter;

@WebServlet(urlPatterns= {"/admin-home"})
public class HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6997164515283054772L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		super.doPost(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IBuildingService buildingService=new BuildingService();
		BuildingDTO buildingDTO=new BuildingDTO();
		buildingDTO.setName("a");
		List<BuildingDTO>result=buildingService.search(buildingDTO,null,new Sorter("name","asc"));
		RequestDispatcher rd=req.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(req, resp);
	}
}
