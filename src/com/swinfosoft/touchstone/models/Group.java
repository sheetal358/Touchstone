package com.swinfosoft.touchstone.models;

public class Group {

	int groupId;
	String name,description,code,members;
	int status;

	public static final int active=1;
	public static final int locked=2;
	public static final int archived=3;
		
	public Group() {
		super();
	}
	
	public Group(String name, String description, String code, String members,
			int status) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.members = members;
		this.status = status;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
