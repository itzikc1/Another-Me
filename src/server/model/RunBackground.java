package server.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import server.controller.Controller;
import server.view.View;
import servlet.view.ServlerViewInterface;

/**
 * Application Lifecycle Listener implementation class RunBackground
 *
 */
@WebListener
public class RunBackground implements  ServletContextListener {

	
	
	
	private ScheduledExecutorService scheduler;
    /**
     * Default constructor. 
     */
    public RunBackground() {


    }

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		 scheduler.scheduleAtFixedRate(new Model(), 0, 60, TimeUnit.SECONDS);
//		 View view= new View();
//			ModelController model = new ModelController();
//			Controller controller = new Controller(model, view);
//			ServlerViewInterface servlets = new ServlerViewInterface();
//			view.SetController(controller,servlets);
//			servlets.setView(view);
	}
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		scheduler.shutdownNow();
	}

}
