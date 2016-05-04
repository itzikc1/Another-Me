package server.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		 scheduler.scheduleAtFixedRate(new Model(), 0, 30, TimeUnit.SECONDS);
			
	}
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		scheduler.shutdownNow();
	}



	
	
}
