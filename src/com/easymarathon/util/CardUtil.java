package com.easymarathon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import com.easymarathon.bean.IDCard;

public class CardUtil
{
	public static String curpath;
	private static Random rand;

	private native static boolean init(String path);

	private native static boolean hasCard();

	private native static String[] readCard();

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
			boolean isSuc = init(curpath);
			System.out.println("cardutil dll init : " + isSuc);
		}
		catch (UnsatisfiedLinkError e)
		{
			System.err.println("error when load library");
			System.err.println(e.getMessage());
			throw e;
		}
	}

	public static boolean testHasCard()
	{
		// return hasCard();
		int r = rand.nextInt(256);
		return (r > 250);
	}

	public static IDCard getIDCard()
	{
		IDCard idCard = new IDCard();
		String[] strs = readCard();
		System.out.println("readCard");
		for (String str : strs)
			System.out.println(str);
		idCard.setName(strs[0]);
		idCard.setId(strs[1]);
		idCard.setGender(strs[2].equals("male"));
		idCard.setHead(strs[3]);
		try (FileInputStream fis = new FileInputStream(new File(curpath + "temp.jpg")))
		{
			byte[] dat = new byte[fis.available()];
			fis.read(dat);
			idCard._setImg(dat);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return idCard;
	}

}
