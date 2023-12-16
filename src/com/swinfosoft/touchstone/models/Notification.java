package com.swinfosoft.touchstone.models;

public class Notification {
	int id;
	String notification,userName,createdOn;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Notification(String notification, String userName, String createdOn) {
		this.notification = notification;
		this.userName = userName;
		this.createdOn = createdOn;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
