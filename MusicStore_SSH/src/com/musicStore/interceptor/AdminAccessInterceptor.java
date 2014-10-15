package com.musicStore.interceptor;

import java.util.Map;

import com.musicStore.bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminAccessInterceptor extends AbstractInterceptor {
	
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		User user = (User)session.get("loginUser");
		if(user == null || (!user.getUserRole().getName().equals("管理员")) )
			return Action.LOGIN;
		else
			return invocation.invoke();
	}
}
