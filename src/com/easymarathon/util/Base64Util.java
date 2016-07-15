package com.easymarathon.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Util
{
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();

	public static String encode(byte[] dat)
	{
		if (dat == null)
			return "";
		else
			return encoder.encodeToString(dat);
	}

	public static byte[] decode(String str)
	{
		if (str == null)
			return new byte[0];
		else
			return decoder.decode(str);
	}
}
