package com.swinfosoft.touchstone.models;

public class Message {
		int id;		
		String message,userName,createdOn,groupName;
		
		public Message() {
			super();
		}
		
		public Message(String message, String userName, String createdOn,
				String groupName) {
			this.message = message;
			this.userName = userName;
			this.createdOn = createdOn;
			this.groupName = groupName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
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

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
			
		
}
