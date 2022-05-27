package kr.or.ddit.basic;


import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("[MyServletContextListener] contextDestroyed 호출됨.");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("[MyServletContextListener] contextDestroyed 호출됨.");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListener] attributeAdded 호출됨. => " + arg0.getName());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListener] attributeRemoved 호출됨. => " + arg0.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListener] attributeReplaced 호출됨. => " + arg0.getName());
	}

	
	
}
