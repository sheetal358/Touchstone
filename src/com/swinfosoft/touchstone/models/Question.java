package com.swinfosoft.touchstone.models;

public class Question {
int id,type,difficultyLevel,ans,quizId;
String description;
String options[];

public static final int TF=1;
public static final int MC=2;

public static final int EASY=1;
public static final int MEDIUM=2;
public static final int HARD=3;

public Question() {
	super();
}



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public int getDifficultyLevel() {
	return difficultyLevel;
}

public void setDifficultyLevel(int difficultyLevel) {
	this.difficultyLevel = difficultyLevel;
}

public int getAns() {
	return ans;
}

public void setAns(int ans) {
	this.ans = ans;
}

public int getQuizId() {
	return quizId;
}

public void setQuizId(int quizId) {
	this.quizId = quizId;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



public String[] getOptions() {
	return options;
}



public void setOptions(String[] options) {
	this.options = options;
}



}
