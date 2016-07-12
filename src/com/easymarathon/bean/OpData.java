package com.easymarathon.bean;

import java.util.HashMap;
import java.util.Map;

public class OpData
{
	private String act = "";
	private String msg = "";
	private Map<String, String> datmap = new HashMap<String, String>();

	public OpData()
	{
	}

	public OpData(String act, String msg)
	{
		this.act = act;
		this.msg = msg;
	}

	public String getAct()
	{
		return act;
	}

	public void setAct(String act)
	{
		this.act = act;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Map<String, String> getDatmap()
	{
		return datmap;
	}

	public void setDatmap(Map<String, String> datmap)
	{
		this.datmap = datmap;
	}

	public OpData add(String key, String val)
	{
		datmap.put(key, val);
		return this;
	}

	public String get(String key)
	{
		return datmap.get(key);
	}
}
