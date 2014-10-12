package com.myCommunity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="CommunityUser")
public class User implements Serializable  {
	public User(){}
	public User(int id){
		this.id = id;
	}
	
	@Id
	@SequenceGenerator(name="seqUser", sequenceName="seq_community_user", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqUser")
	private int id;
	@Column
	private String username;
	@Column
	private String nickname;
	@Column
	private String password;
	@Column
	private String signature;
	@Column
	private String email;
	@Column
	private int grade;
	@ManyToOne
	@JoinColumn(name="RoleId")
	private Role role;
	@Column
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
