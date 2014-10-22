package com.codeLibrary.entity;

public class CodeInfo {
	public CodeInfo(){}
	public CodeInfo(int id, String title, Category category, String keys,
			String content, Integer points) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.keys = keys;
		this.content = content;
		this.points = points;
	}
	
	private int id;
	private String title;
	private Category category;
	private String keys;
	private String content;
	private Integer points;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
}
