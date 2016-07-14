package com.easymarathon.service;

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

	private static void onUpImg()
	{
		WSHost.BroadCast(new OpData("msg", "received upload image"));
	}

	public static OpData act(OpData op)
	{
		OpData ret = null;
		switch (op.getAct())
		{
		case "init":
			return new OpData("log", "received");
		case "getcard":
			IDCard idCard = CardUtil.getIDCard();
			onGetCard(idCard);
			return null;
		case "upimg":
			System.out.println("receive the upload image");
			MainService.uploadHead(Base64.getDecoder().decode(op.getMsg()));
			onUpImg();
			return null;
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
