package com.myCommunity.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CommunityMessageSending")
public class MessageSending {
	public static final int STATUS_DRAFT=0;
	public static final int STATUS_SENDED=1;
	public static final int STATUS_RECYCLE=2;
	public static final int STATUS_DELETED=3;
	
	public MessageSending(){}
	public MessageSending(int id, String title, String content, String receivers,
			Timestamp sendTime, User sender, int status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.receivers = receivers;
		this.sendTime = sendTime;
		this.sender = sender;
		this.status = status;
	}
	@Id
	@SequenceGenerator(name="seqMessageSendingr", sequenceName="seq_community_message_sending", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMessageSendingr")
	private int id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String receivers;
	@Column
	private Timestamp sendTime;
	@ManyToOne
	@JoinColumn(name="SenderId")
	private User sender;
	@Column
	private int status;
	@OneToMany(mappedBy="messageSending",fetch=FetchType.EAGER)
	private List<MessageReceiving> messageReceivingList;
	
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
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<MessageReceiving> getMessageReceivingList() {
		return messageReceivingList;
	}
	public void setMessageReceivingList(List<MessageReceiving> messageReceivingList) {
		this.messageReceivingList = messageReceivingList;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	//原不增加receivers字段时的方法
//	public String getReceivers(){
//		if(messageReceivingList.size()==0)
//			return "";
//		
//		StringBuilder sb = new StringBuilder();
//		for(int i=0; i<messageReceivingList.size()-1; i++){
//			sb.append(messageReceivingList.get(i).getReceiver().getUsername()).append(",");
//		}
//		sb.append(messageReceivingList.get(messageReceivingList.size()-1).getReceiver().getUsername());
//		return sb.toString();
//	}
}
