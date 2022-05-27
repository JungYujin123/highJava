package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener{

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("[MySessionBindingListener]" + "valueBound=>" + arg0.getName() + ":" + arg0.getValue());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("[MySessionBindingListener]" + "valueUnbound=>" + arg0.getName() + ":" + arg0.getValue());
	}

}
