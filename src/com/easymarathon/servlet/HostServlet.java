package com.easymarathon.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.easymarathon.bean.OpData;
import com.easymarathon.service.HostService;

@WebServlet("/host")
public class HostServlet extends HttpServlet
{

	private static final long serialVersionUID = 3043839253861599744L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		String ret = JSON.toJSONString(new OpData("test", "onGet"));
		try (PrintWriter out = response.getWriter();)
		{
			out.write(ret);
			out.flush();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		String txt = null;
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));)
		{
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null)
			{
				sb.append(temp);
			}
			txt = sb.toString();
		}
		if (txt != null)
		{
			OpData op = JSON.parseObject(txt, OpData.class);
			OpData resop = HostService.act(op);
			response.setContentType("text/html; charset=UTF-8");
			if (resop != null)
			{
				String ret = JSON.toJSONString(resop);
				try (PrintWriter out = response.getWriter();)
				{
					out.write(ret);
					out.flush();
				}
			}
		}
	}
}
