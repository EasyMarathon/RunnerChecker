package com.easymarathon.bean;

import com.easymarathon.util.Base64Util;

public class IDCard
{
	private String name = "";
	private String id = "";
	private boolean gender = true;
	private byte[] img;
	private String head;

	public IDCard()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean isGender()
	{
		return gender;
	}

	public void setGender(boolean gender)
	{
		this.gender = gender;
	}

	public byte[] _getImg()
	{
		return img;
	}

	public void _setImg(byte[] img)
	{
		this.img = img;
		head = Base64Util.encode(img);
	}

	public String getHead()
	{
		return head;
	}

	public void setHead(String head)
	{
		this.head = head;
		img = Base64Util.decode(head);
	}

}
