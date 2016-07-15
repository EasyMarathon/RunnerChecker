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
	 * ��¼�׶Σ���������Ա��������Ƭ����Ƭ���ϴ�������֤ʱ�Ĳ����߼�
	 * 
	 * @param img
	 *            �����ͼ������
	 * @return �����˶�Ա���룬-1��ʾ��֤��ͨ��
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
	 * ��ȡ�˶�Ա������ʱ�����ͼ��
	 * 
	 * @param id
	 *            �˶�Ա�ĺ����Ʊ��
	 * @return �����˶�Ա��ͼ��nullΪ�Ҳ������˶�Ա
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
	 * ��ȡ�˶�Ա����Ϣ
	 * 
	 * @param id
	 *            �˶�Ա�ĺ����Ʊ��
	 * @return �����˶�Ա��Ϣ��nullΪ�Ҳ������˶�Ա
	 */
	public static Object getAthleteData(int id)
	{
		return "under construction";
	}

	/**
	 * ��ȡ�˶�Ա����Ϣ
	 * 
	 * @param idCard
	 *            ʶ�𵽵����֤��Ϣ
	 * @return �����˶�Ա��Ϣ��nullΪ�Ҳ������˶�Ա
	 */
	public static Object getAthleteData(IDCard idCard)
	{
		return "under construction";
	}

}
