package com.easymarathon.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.easymarathon.bean.IDCard;
import com.easymarathon.bean.OpData;
import com.easymarathon.servlet.WSHost;
import com.easymarathon.util.CardUtil;

public class HostService
{
	public static void onGetCard(IDCard idCard)
	{
		OpData ret = new OpData("idcard", "get card").add("idcard", JSON.toJSONString(idCard));
		WSHost.BroadCast(ret);
	}

	public static void onNoCard()
	{
		WSHost.BroadCast(new OpData("idcard", "no card"));
	}

	public static OpData act(OpData op)
	{
		OpData ret = null;
		switch (op.getAct())
		{
		case "init":
			return new OpData("log", "received");
		case "getcard":
			IDCard idCard = new IDCard();
			try (FileInputStream fis = new FileInputStream(new File(CardUtil.curpath + "temp.jpg")))
			{
				byte[] dat = new byte[fis.available()];
				fis.read(dat);
				idCard.setImg(dat);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			onGetCard(idCard);
			return null;
		case "upimg":
			System.out.println("receive the upload image");
			MainService.uploadHead(Base64.getDecoder().decode(op.getMsg()));
			break;
		case "validate":
			System.out.println("receive the upload image");
			boolean onValid = MainService.validate(Base64.getDecoder().decode(op.getMsg()));
			return new OpData("result", String.valueOf(onValid)).add("head",
					CardUtil.getIDCard().getHead());
		default:
			break;
		}
		return ret;
	}

}
