package com.easymarathon.service;

import com.alibaba.fastjson.JSON;
import com.easymarathon.bean.IDCard;
import com.easymarathon.bean.OpData;
import com.easymarathon.servlet.WSHost;
import com.easymarathon.util.Base64Util;
import com.easymarathon.util.CardUtil;

public class HostService
{
	public static void onGetCard(IDCard idCard)
	{
		if (idCard == null)
		{
			onNoCard();
			return;
		}
		// IDCard data
		OpData ret = new OpData("idcard", "get card").add("idcard", JSON.toJSONString(idCard));
		// athlete data
		ret.add("athlete", JSON.toJSONString(MainService.getAthleteData(idCard)));
		WSHost.BroadCast(ret);
	}

	public static void onNoCard()
	{
		WSHost.BroadCast(new OpData("idcard", "no card"));
	}

	private static void onUpImg(String res)
	{
		WSHost.BroadCast(new OpData("msg", "received upload image").add("result", res));
	}

	private static OpData onValidate(int id)
	{
		OpData ret;
		if (id == -1)
			ret = new OpData("result", "false");
		else
		{
			ret = new OpData("result", "true");
			// athlete data
			ret.add("athlete", JSON.toJSONString(MainService.getAthleteData(id)));
			// last day head img
			ret.add("head", Base64Util.encode(MainService.getHeadImg(id)));
		}
		return ret;
	}

	public static OpData act(OpData op)
	{
		int id = -1;
		byte[] img = null;
		switch (op.getAct())
		{
		case "init":
			return new OpData("log", "received");
		case "getcard":
			IDCard idCard = CardUtil.getIDCard();
			onGetCard(idCard);
			break;
		case "upimg":
			System.out.println("receive the upload image");
			String sid = op.get("id");
			if (sid != null)
				id = Integer.parseInt(sid);
			img = Base64Util.decode(op.getMsg());
			String res = MainService.uploadHead(img, id);
			onUpImg(res);
			break;
		case "validate":
			System.out.println("receive the upload image");
			img = Base64Util.decode(op.getMsg());
			return onValidate(MainService.validate(img));
		default:
			break;
		}
		return null;
	}

}
