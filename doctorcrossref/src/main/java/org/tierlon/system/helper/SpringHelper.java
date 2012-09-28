package org.tierlon.system.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
	
		applicationContext = context;
		
	}
	
	public static ApplicationContext getApplicationContext() {
		if (applicationContext==null)
			throw new NullPointerException();
		return applicationContext;
	}

}
