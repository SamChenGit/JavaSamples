package com.myCommunity.ui.listener;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.myCommunity.biz.UserBiz;
import com.myCommunity.ui.util.ContextUtils;

public class DataCacheListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent sce) {
		//Do nothing
	}

	public void contextInitialized(ServletContextEvent sce) {
		UserBiz userBiz = (UserBiz)ContextUtils.getBeanFactory().getBean("userBiz");
		ContextUtils.setUsersMap(userBiz.getAllUsersKeyValuePair());
	}

}
