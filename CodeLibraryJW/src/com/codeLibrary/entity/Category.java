package com.codeLibrary.entity;

public class Category {
	public Category(){}
	
	public Category(int id, Integer parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	
	private int id;
	private Integer parentId;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
