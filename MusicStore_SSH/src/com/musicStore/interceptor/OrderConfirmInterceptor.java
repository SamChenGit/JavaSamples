package com.musicStore.interceptor;

import java.util.Map;

import com.musicStore.bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class OrderConfirmInterceptor extends AbstractInterceptor {
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		User user = (User)session.get("loginUser");
		if(user == null)
			return Action.LOGIN;
		else
			return invocation.invoke();
	}
}
