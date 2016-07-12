package com.easymarathon.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainService
{

	public MainService()
	{
	}

	public static String uploadHead(byte[] img)
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

	public static boolean validate(byte[] img)
	{
		return (uploadHead(img) != null);
	}

}
