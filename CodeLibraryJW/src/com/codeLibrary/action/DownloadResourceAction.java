package com.codeLibrary.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.codeLibrary.biz.ResourceBiz;
import com.codeLibrary.entity.Resource;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadResourceAction extends ActionSupport {
	private ResourceBiz resourceBiz;
	public void setResourceBiz(ResourceBiz resourceBiz) {
		this.resourceBiz = resourceBiz;
	}

	private int resourceId;
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	private String downloadFileName;
	private String filePath;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
	public InputStream getInputStream() throws Exception{
		return new FileInputStream(this.filePath);
	}

	public String execute() throws UnsupportedEncodingException{
		Resource resource = resourceBiz.fetchByKey(resourceId);
		this.downloadFileName = new String(resource.getFilename().getBytes(), "ISO-8859-1");
		this.filePath = ServletActionContext.getServletContext().getRealPath(resource.getLocation());
		return SUCCESS;
	}
}
