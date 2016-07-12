package com.easymarathon.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.easymarathon.bean.OpData;
import com.easymarathon.service.HostService;

@ServerEndpoint(value = "/ws/host")
public class WSHost
{
	public static final Set<Session> connections = new CopyOnWriteArraySet<Session>();
	private Session session;

	@OnOpen
	public void onOpen(Session session, EndpointConfig arg1)
	{
		connections.add(session);
		this.session = session;
		System.out.println("new user id=" + session.getId());
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason)
	{
		connections.remove(session);
		System.out.println("Close id=" + session.getId());
		System.out.println(closeReason.getReasonPhrase());
	}

	@OnError
	public void onError(Session session, java.lang.Throwable throwable)
	{
		System.out.println("error id=" + session.getId());
	}

	@OnMessage
	public void onMessage(Session session, String msg)
	{
		System.out.println("------" + msg);
		OpData op = JSON.parseObject(msg, OpData.class);
		OpData resop = HostService.act(op);
		if (resop != null)
			Send(resop, session);
	}

	private static Boolean Send(OpData op, Session wss)
	{
		String data = JSON.toJSONString(op);
		try
		{
			wss.getBasicRemote().sendText(data);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try
			{
				wss.close();
			}
			catch (IOException e1)
			{
				return null;
			}
			return false;
		}
	}

	public static int BroadCast(OpData op)
	{
		int num = 0;
		synchronized (connections)
		{
			for (Session wss : connections)
			{
				if (Send(op, wss) == true)
				{
					System.out.println("send id=" + wss.getId());
					num++;
				}
				else
				{
					System.out.println("id=" + wss.getId() + " leaves.");
					connections.remove(wss);
				}
			}
		}
		return num;
	}

}
