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
@Table(name="CommunityTopic")
public class Topic implements Serializable {

	public Topic() {
	}
	
	public Topic(int id, String title, String content, Timestamp createTime,
			Forum forum, User author, String ip, int commentTimes,
			Timestamp updateTime, int clicks, boolean isBest, boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.forum = forum;
		this.author = author;
		this.ip = ip;
		this.commentTimes = commentTimes;
		this.updateTime = updateTime;
		this.clicks = clicks;
		this.isBest = isBest;
		this.status = status;
	}

	@Id
	@SequenceGenerator(name="seqTopic", sequenceName="seq_community_topic", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqTopic")
	private int id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private Timestamp createTime;
	@ManyToOne
	@JoinColumn(name="ForumId")
	private Forum forum;
	@ManyToOne
	@JoinColumn(name="AuthorId")
	private User author;
	@Column
	private String ip;
	@Column
	private int commentTimes;
	@Column
	private Timestamp updateTime;
	@Column
	private int clicks;
	@Column
	private boolean isBest;
	@Column
	private boolean status;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCommentTimes() {
		return commentTimes;
	}
	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public boolean isBest() {
		return isBest;
	}
	public void setBest(boolean isBest) {
		this.isBest = isBest;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
