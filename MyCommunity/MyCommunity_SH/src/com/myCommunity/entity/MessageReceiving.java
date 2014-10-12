package com.myCommunity.entity;

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
@Table(name="CommunityMessageReceiving")
public class MessageReceiving {
	public static final int STATUS_NORMAL=1;
	public static final int STATUS_RECYCLE=2;
	public static final int STATUS_DELETED=3;
	
	public MessageReceiving(){}
	public MessageReceiving(int id, MessageSending messageSending,
			User receiver, boolean readed, int status) {
		super();
		this.id = id;
		this.messageSending = messageSending;
		this.receiver = receiver;
		this.readed = readed;
		this.status = status;
	}
	@Id
	@SequenceGenerator(name="seqMessageReceiving", sequenceName="seq_community_message_recei", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMessageReceiving")
	private int id;
	@ManyToOne
	@JoinColumn(name="MessageSendingId")
	private MessageSending messageSending;
	@ManyToOne
	@JoinColumn(name="ReceiverId")
	private User receiver;
	@Column
	private boolean readed;
	@Column
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MessageSending getMessageSending() {
		return messageSending;
	}
	public void setMessageSending(MessageSending messageSending) {
		this.messageSending = messageSending;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public boolean getReaded() {
		return readed;
	}
	public void setReaded(boolean reader) {
		this.readed = reader;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
