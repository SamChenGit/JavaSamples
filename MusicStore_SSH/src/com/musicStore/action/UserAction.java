package com.musicStore.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.musicStore.bean.User;
import com.musicStore.biz.UserBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	
	private UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	private String errorMsg;
	private User user;
	public String getErrorMsg(){
		return this.errorMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public String login(){
		User user = userBiz.checkLogin(this.user.getUsername(), this.user.getPassword());
		if(user != null){
			ActionContext.getContext().getSession().put("loginUser", user);
			return SUCCESS;
		}else{
			this.errorMsg = "用户名或密码有误.";
			return ERROR;
		}
	}
	
	public String register(){
		userBiz.register(user);
		return SUCCESS;
	}
	
	private boolean usernameIsDuplicated;
	public boolean getUsernameIsDuplicated(){
		return this.usernameIsDuplicated;
	}
	
	public String checkUsernameNotDuplicated() throws Exception{
		User user = userBiz.fetchUserByUsername(this.user.getUsername());
		String text = (user!=null?"false":"true");
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(text); 
        return SUCCESS;
	}
}
