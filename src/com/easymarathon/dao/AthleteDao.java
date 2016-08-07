package com.easymarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.easymarathon.bean.Athlete;


public class AthleteDao {
	Connection conn = null;

	public AthleteDao(Connection c) {
		conn = c;
	}

	public Athlete GetAthletebyID(String ID) throws SQLException {
		System.out.println("ID:"+ID);
		final String sql1 = "select * from Athletes1 where IDcard=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
			ps1.setString(1, ID);
			ResultSet rs1 = ps1.executeQuery();
			Athlete athlete=new Athlete();
			if (rs1.next())// no match
			{
				
				athlete.setIDcard(ID);
				athlete.setAddress(rs1.getString("Address"));
				athlete.setAthleteID(rs1.getInt("AthleteID"));
				athlete.setGender(rs1.getBoolean("Gender"));
				athlete.setName(rs1.getString("name"));
				athlete.setPhone(rs1.getString("Phone"));
				athlete.setUrgencyName(rs1.getString("UrgencyName"));
				athlete.setUrgencyPhone(rs1.getString("UrgencyPhone"));
				System.out.println(athlete.getIDcard());
				athlete.setBlood(rs1.getString("Blood"));
				athlete.setIdentityPic(rs1.getString("IdentityPic"));
				return athlete;	
				
			}
			return null;
			
		}
	}
	
	public Athlete GetAthletebyAthleteID(int ID) throws SQLException {
		final String sql1 = "select * from Athletes1 where AthleteID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
			ps1.setInt(1, ID);
			ResultSet rs1 = ps1.executeQuery();
			Athlete athlete=new Athlete();
			if (rs1.next())// no match
			{	
			athlete.setIDcard(rs1.getString("IDcard"));
				athlete.setAddress(rs1.getString("Address"));
				athlete.setAthleteID(ID);
				athlete.setGender(rs1.getBoolean("Gender"));
				athlete.setName(rs1.getString("name"));
				athlete.setPhone(rs1.getString("Phone"));
				athlete.setUrgencyName(rs1.getString("UrgencyName"));
				athlete.setUrgencyPhone(rs1.getString("UrgencyPhone"));
				athlete.setBlood(rs1.getString("Blood"));
				athlete.setIdentityPic(rs1.getString("IdentityPic"));
			}
			return athlete;	
		}
	}
	public ArrayList<Athlete> GetAllAthletes() {
		final String SQL = "select * frome Athletes1";
		ArrayList<Athlete> dataList = new ArrayList<Athlete>();
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ResultSet rs1;
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Athlete athlete=new Athlete();
				athlete.setIDcard(rs1.getString("IDcard"));
				athlete.setAddress(rs1.getString("Address"));
				athlete.setAthleteID(rs1.getInt("IDcard"));
				athlete.setGender(rs1.getBoolean("Gender"));
				athlete.setName(rs1.getString("name"));
				athlete.setPhone(rs1.getString("Phone"));
				athlete.setUrgencyName(rs1.getString("UrgencyName"));
				athlete.setUrgencyPhone(rs1.getString("UrgencyPhone"));
				athlete.setBlood(rs1.getString("Blood"));
				athlete.setIdentityPic(rs1.getString("IdentityPic"));
				dataList.add(athlete);
			}
			return dataList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
