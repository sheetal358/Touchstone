package com.swinfosoft.touchstone.models;

public class Quiz {
int id,status,duration,ownerId,attempted;
String name,createdOn;

public static final int ASSIGNED=1;
public static final int UNASSIGNED=2;

public Quiz() {
	super();
}

public Quiz(int status, int duration, int ownerId, String name, String createdOn) {
	this.status = status;
	this.duration = duration;
	this.ownerId = ownerId;
	this.name = name;
	this.createdOn = createdOn;
}

public int getAttempted() {
	return attempted;
}

public void setAttempted(int attempted) {
	this.attempted = attempted;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public int getOwnerId() {
	return ownerId;
}

public void setOwnerId(int ownerId) {
	this.ownerId = ownerId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}



}
