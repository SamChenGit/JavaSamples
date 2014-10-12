package com.myCommunity.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CommunityForum")
public class Forum implements Serializable {
	
	public Forum(){}
	public Forum(int id, String name, Forum parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name="ParentId")
	private Forum parent;
	@OneToMany(mappedBy="parent")
	private List<Forum> children;
	
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
	public Forum getParent() {
		return parent;
	}
	public void setParent(Forum parent) {
		this.parent = parent;
	}
	public List<Forum> getChildren() {
		return children;
	}
	public void setChildren(List<Forum> children) {
		this.children = children;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
