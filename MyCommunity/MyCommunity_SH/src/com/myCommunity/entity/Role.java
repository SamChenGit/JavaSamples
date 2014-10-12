package com.myCommunity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CommunityRole")
public class Role implements Serializable  {
	public Role(){}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Id
	private int id;
	@Column
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static final int COMMON_USER=1;
	public static final int ADMINISTRATOR=1;
}
