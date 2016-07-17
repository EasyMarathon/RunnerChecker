package com.easymarathon.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import com.easymarathon.bean.IDCard;

public class CardUtil
{
	public static String curpath;
	private static Random rand;

	private native static int init(String path);

	private native static boolean exit();

	private native static boolean hasCard();

	private native static byte[][] readCard();

	static
	{
		try
		{
			curpath = new URI(CardUtil.class.getResource("").getPath()).getPath().substring(1);
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		rand = new Random();
		try
		{
			System.load(curpath + System.mapLibraryName("IDCardReader"));
		}
		catch (UnsatisfiedLinkError e)
		{
			System.err.println("error when load library");
			System.err.println(e.getMessage());
		}
	}

	public static void onInit()
	{
		int retcode = init(curpath);
		System.out.println("cardutil dll init : " + retcode);
	}

	public static void onExit()
	{
		exit();
	}

	public static boolean testHasCard()
	{
		return hasCard();
		// int r = rand.nextInt(256);
		// return (r > 250);
	}

	public static IDCard getIDCard()
	{
		IDCard idCard = new IDCard();
		byte[][] rets = readCard();
		if (rets == null)
		{
			System.out.println("noCard");
			return null;
		}
		System.out.println("readCard");
		try
		{
			idCard.setName(new String(rets[0], "UTF-16LE"));
			idCard.setId(new String(rets[1], "UTF-16LE"));
			idCard.setGender(new String(rets[2], "UTF-16LE").equals("ÄÐ"));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		idCard._setImg(rets[3]);
		return idCard;
	}

}
