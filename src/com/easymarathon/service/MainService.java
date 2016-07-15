package com.easymarathon.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.easymarathon.bean.IDCard;
import com.easymarathon.util.CardUtil;

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
		try (FileOutputStream fos = new FileOutputStream(
				new File("D:\\" + System.currentTimeMillis() + ".png")))
		{
			fos.write(img);
			return "";
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 检录阶段，当工作人员拍摄完照片，照片被上传进行验证时的操作逻辑
	 * 
	 * @param img
	 *            拍摄的图像数据
	 * @return 返回运动员号码，-1表示验证不通过
	 */
	public static int validate(byte[] img)
	{
		try (FileOutputStream fos = new FileOutputStream(
				new File("D:\\" + System.currentTimeMillis() + ".png")))
		{
			fos.write(img);
			return 10086;
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
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
		try (FileInputStream fis = new FileInputStream(new File(CardUtil.curpath + "temp.jpg")))
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
		return "under construction";
	}

	/**
	 * 获取运动员的信息
	 * 
	 * @param idCard
	 *            识别到的身份证信息
	 * @return 返回运动员信息，null为找不到此运动员
	 */
	public static Object getAthleteData(IDCard idCard)
	{
		return "under construction";
	}

}
