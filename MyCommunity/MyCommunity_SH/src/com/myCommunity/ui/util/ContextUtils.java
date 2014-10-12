package com.myCommunity.ui.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class ContextUtils implements BeanFactoryAware {
	
	private static BeanFactory beanFactory;
	public static BeanFactory getBeanFactory(){
		return beanFactory;
	}
	
	private static Map<String, Integer> usersMap;
	public static Map<String, Integer> getUsersMap() {
		return usersMap;
	}
	public static void setUsersMap(Map<String, Integer> usersMap) {
		ContextUtils.usersMap = usersMap;
	}

	public void setBeanFactory(BeanFactory factory) throws BeansException {
		beanFactory = factory; 
	}

}
