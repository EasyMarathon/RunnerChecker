package com.easymarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.*;


public class DaoBase
{
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	public static Connection getConnection(boolean auto)
	{
		try
		{
			Connection conn = cpds.getConnection();
			conn.setAutoCommit(auto);
			return conn;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void close(Connection conn, PreparedStatement pst,
			ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (pst != null)
		{
			try
			{
				pst.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		if (conn != null)
		{
			try
			{
				conn.setAutoCommit(true);
				conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	} 
}
