package com.myCommunity.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="CommunityComment")
public class Comment implements Serializable  {
	public Comment(){}
	public Comment(int id, String content, Timestamp commentTime, String ip,
			int commentType, Topic reference, User commenter, boolean status) {
		super();
		this.id = id;
		this.content = content;
		this.commentTime = commentTime;
		this.ip = ip;
		this.commentType = commentType;
		this.reference = reference;
		Commenter = commenter;
		this.status = status;
	}
	
	@Id
	@SequenceGenerator(name="seqComment", sequenceName="seq_community_comment", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqComment")
	private int id;
	@Column
	private String content;
	@Column
	private Timestamp commentTime;
	@Column
	private String ip;
	@Column
	private int commentType = 1;
	@ManyToOne
	@JoinColumn(name="ReferenceId")
	private Topic reference;
	@ManyToOne
	@JoinColumn(name="CommenterId")
	private User Commenter;
	@Column
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCommentType() {
		return commentType;
	}
	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}
	public Topic getReference() {
		return reference;
	}
	public void setReference(Topic reference) {
		this.reference = reference;
	}
	public User getCommenter() {
		return Commenter;
	}
	public void setCommenter(User commenter) {
		Commenter = commenter;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
