package com.musicStore.action;

import com.opensymphony.xwork2.ActionSupport;

public class FrontTipsAction extends ActionSupport {
	private String message;
	private String url;
	private int seconds;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String execute(){
		return SUCCESS;
	}
}
