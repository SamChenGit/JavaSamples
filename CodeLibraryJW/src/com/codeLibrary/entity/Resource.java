package com.codeLibrary.entity;

public class Resource {
	public Resource(){}
	public Resource(int id, CodeInfo codeInfo, String title, String location, String resourceType) {
		super();
		this.id = id;
		this.codeInfo = codeInfo;
		this.title = title;
		this.location = location;
		this.resourceType = resourceType;
	}
	private int id;
	private CodeInfo codeInfo;
	private String title;
	private String location;
	private String resourceType;
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFilename(){
		return this.title+"."+this.resourceType;
	}
}
