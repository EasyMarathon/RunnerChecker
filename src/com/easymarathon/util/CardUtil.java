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

	private native static boolean hasCard();

	public static boolean testHasCard()
	{
		// return hasCard();
		int r = rand.nextInt(256);
		return (r > 250);
	}

	public static IDCard getIDCard()
	{
		IDCard idCard = new IDCard();
		try (FileInputStream fis = new FileInputStream(new File(curpath + "temp.jpg")))
		{
			byte[] dat = new byte[fis.available()];
			fis.read(dat);
			idCard.setImg(dat);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return idCard;
	}

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
		// System.load(curpath + System.mapLibraryName("CardUtil"));
	}
}
