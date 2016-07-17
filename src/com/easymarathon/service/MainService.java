package com.easymarathon.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.easymarathon.dao.AthleteDao;
import com.easymarathon.dao.DaoBase;
import com.easymarathon.bean.Athlete;
import com.easymarathon.bean.IDCard;
import com.easymarathon.util.NumIdentify;

import com.easymarathon.util.Facerecognition;
import com.easymarathon.util.CameraUtil;
import com.easymarathon.util.FaceAlignment;

public class MainService
{

	/**
	 * 拿物资阶段，当身份验证通过，上传当日照片时的操作逻辑
	 * 
	 * @param img
	 *            拍摄的图像数据
	 * @param id
	 *            运动员的号码牌编号
	 * @return 可选，返回本次操作的结果等
	 */
	public static String uploadHead(byte[] img, int id)
	{
		String fname="G:\\face\\athleteCheck\\initial\\" + id + ".jpg";
		System.out.println(fname);
		try (FileOutputStream fos = new FileOutputStream(
				new File(fname)))
		{
			fos.write(img);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		String dname="G:\\face\\athleteCheck\\alignment\\" + id;
		System.out.println(dname);
		FaceAlignment ni = new FaceAlignment();
		System.out.println("run dll");
		try{
			int ans = ni.cutface(fname, dname);
			System.out.println("ans:"+ans);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	/**
	 * 检录阶段，当工作人员拍摄完照片，照片被上传进行验证时的操作逻辑
	 * 
	 * @param img
	 *            拍摄的图像数据
	 * @return 返回运动员号码，0表示不存在此运动员，返回号码牌的负数表示验证不通过
	 */
	public static int validate(byte[] img)
	{
		try (FileOutputStream fos = new FileOutputStream(
				new File("G:\\face\\athleteCheck\\temp.jpg")))
		{
			fos.write(img);
			
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		NumIdentify ni = new NumIdentify();
		int ans = ni.GetID("G:\\face\\athleteCheck\\temp.jpg");
		//int ans=2345;
		System.out.println(ans);
		CameraUtil cameraUtil =new CameraUtil();
		int ss=cameraUtil.compare(ans);
		System.out.println(ss);
		if(ss<50000)
		   return ans;
		else
			return -1;
	}

	/**
	 * 获取运动员拿物资时拍摄的图像
	 * 
	 * @param id
	 *            运动员的号码牌编号
	 * @return 返回运动员的图像，null为找不到此运动员
	 */
	public static byte[] getHeadImg(int id)
	{
		try (FileInputStream fis = new FileInputStream(new File("G:\\face\\athleteCheck\\initial\\" + id + "-0.jpg")))
		{
			byte[] dat = new byte[fis.available()];
			fis.read(dat);
			return dat;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取运动员的信息
	 * 
	 * @param id
	 *            运动员的号码牌编号
	 * @return 返回运动员信息，null为找不到此运动员
	 */
	public static Object getAthleteData(int id)
	{
		Connection conn = DaoBase.getConnection(true);
		AthleteDao athleteDao = new AthleteDao(conn);
		Athlete athlete=new Athlete();
		try{
			athlete=athleteDao.GetAthletebyAthleteID(id);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			DaoBase.close(conn, null, null);
		}
		return athlete;
	}

	/**
	 * 获取运动员的信息
	 * 
	 * @param idCard
	 *            识别到的身份证信息
	 * @return 返回运动员信息，null为找不到此运动员
	 * @throws SQLException 
	 */
	public static Object getAthleteData(IDCard idCard) 
	{
		String id=idCard.getId();
		Connection conn = DaoBase.getConnection(true);
		AthleteDao athleteDao = new AthleteDao(conn);
		Athlete athlete=null;
		try{
			athlete=athleteDao.GetAthletebyID(id);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			DaoBase.close(conn, null, null);
		}
		return athlete;
	}

}
