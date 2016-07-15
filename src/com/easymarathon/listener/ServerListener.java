package com.easymarathon.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.easymarathon.service.HostService;
import com.easymarathon.util.CardUtil;

/**
 * Application Lifecycle Listener implementation class ServerListener
 *
 */
@WebListener
public class ServerListener implements ServletContextListener, Runnable
{

	private static Thread listener;
	private static boolean isRun = false;

	/**
	 * Default constructor.
	 */
	public ServerListener()
	{
		listener = new Thread(this);
	}

	public void run()
	{
		System.out.println("IDCard listener started");
		boolean hasCard = false;
		while (isRun)
		{
			boolean newState = CardUtil.testHasCard();
			if (hasCard != newState)
			{
				HostService.onGetCard(CardUtil.getIDCard());
			}
			hasCard = newState;
			try
			{
				Thread.sleep(hasCard ? 3000 : 1000);// wait more time when has card previously
			}
			catch (InterruptedException e)
			{
				if (isRun)
					e.printStackTrace();
			}
		}
		System.out.println("IDCard listener stopped");
		synchronized (this)
		{
			this.notifyAll();
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0)
	{
		isRun = true;
		CardUtil.onInit();
		listener.start();
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)
	{
		isRun = false;
		CardUtil.onExit();
		synchronized (this)
		{
			listener.interrupt();
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
