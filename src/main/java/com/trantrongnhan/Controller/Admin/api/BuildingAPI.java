package com.trantrongnhan.Controller.Admin.api;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.Entity.BuildingEntity;
import com.trantrongnhan.Utils.HttpUtils;

@WebServlet(urlPatterns = { "/api-admin-building" })
public class BuildingAPI extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		BuildingDTO obj=new BuildingDTO();
		obj=HttpUtils.toModel(req.getReader(), obj);
		objectMapper.writeValue(resp.getOutputStream(), obj);
		

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		BuildingDTO obj=new BuildingDTO();
		obj=HttpUtils.toModel(req.getReader(), obj);
		objectMapper.writeValue(resp.getOutputStream(), obj);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		BuildingDTO obj=new BuildingDTO();
		obj=HttpUtils.toModel(req.getReader(), obj);
		objectMapper.writeValue(resp.getOutputStream(), obj);
	}
}
