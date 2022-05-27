package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener]"+"requestDestroyed호출됨.");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener]"+"requestInitialized호출됨.");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener]"+"attributeAdded호출됨. => " + arg0.getName());
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener]"+"attributeRemoved호출됨. => " + arg0.getName());
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener]"+"attributeReplaced호출됨. => " + arg0.getName());
		
	}

}
