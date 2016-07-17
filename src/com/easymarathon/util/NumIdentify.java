package com.easymarathon.util;

import java.net.URI;
import java.net.URISyntaxException;

public class NumIdentify
{
	public static String curpath;
	private native int Identify(String fname, String path);

	public int GetID(String fname)
	{
		return Identify(fname,curpath);
	}
	
	static
	{
		try
		{
			curpath = new URI(NumIdentify.class.getResource("").getPath()).getPath().substring(1);
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		System.load(curpath + System.mapLibraryName("NumIdentify"));
	}
}
