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
	 * �����ʽ׶Σ��������֤ͨ�����ϴ�������Ƭʱ�Ĳ����߼�
	 * 
	 * @param img
	 *            �����ͼ������
	 * @param id
	 *            �˶�Ա�ĺ����Ʊ��
	 * @return ��ѡ�����ر��β����Ľ����
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
	 * ��¼�׶Σ���������Ա��������Ƭ����Ƭ���ϴ�������֤ʱ�Ĳ����߼�
	 * 
	 * @param img
	 *            �����ͼ������
	 * @return �����˶�Ա���룬0��ʾ�����ڴ��˶�Ա�����غ����Ƶĸ�����ʾ��֤��ͨ��
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
	 * ��ȡ�˶�Ա������ʱ�����ͼ��
	 * 
	 * @param id
	 *            �˶�Ա�ĺ����Ʊ��
	 * @return �����˶�Ա��ͼ��nullΪ�Ҳ������˶�Ա
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
	 * ��ȡ�˶�Ա����Ϣ
	 * 
	 * @param id
	 *            �˶�Ա�ĺ����Ʊ��
	 * @return �����˶�Ա��Ϣ��nullΪ�Ҳ������˶�Ա
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
	 * ��ȡ�˶�Ա����Ϣ
	 * 
	 * @param idCard
	 *            ʶ�𵽵����֤��Ϣ
	 * @return �����˶�Ա��Ϣ��nullΪ�Ҳ������˶�Ա
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
