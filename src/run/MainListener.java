package run;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import server.controller.Controller;
import server.model.ModelController;
import server.view.View;
import servlet.view.ServlerViewInterface;

/**
 * Application Lifecycle Listener implementation class MainListener
 *
 */
@WebListener
public class MainListener implements ServletContextListener{

	/**
	 * Default constructor.
	 */
	public MainListener() {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		
			
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Shutting down!");
	}

	

}
