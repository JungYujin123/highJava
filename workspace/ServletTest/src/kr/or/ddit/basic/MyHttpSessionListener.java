package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener{

   @Override
   public void sessionCreated(HttpSessionEvent arg0) {
      System.out.println("[MyHttpSessionListener] sessionCreated.");
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent arg0) {
      System.out.println("[MyHttpSessionListener] sessionDestroyed.");
      
   }

   @Override
   public void attributeAdded(HttpSessionBindingEvent arg0) {
      System.out.println("[MyHttpSessionListener] attributeAdded.=> "+arg0.getName()+" : "+arg0.getValue());
      
   }

   @Override
   public void attributeRemoved(HttpSessionBindingEvent arg0) {
      System.out.println("[MyHttpSessionListener] attributeRemoved. => "+arg0.getName()+" : "+arg0.getValue());
      
   }

   @Override
   public void attributeReplaced(HttpSessionBindingEvent arg0) {
      System.out.println("[MyHttpSessionListener] attributeReplaced.=> "+arg0.getName()+" : "+arg0.getValue());
      
   }

}