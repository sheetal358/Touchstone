package com.swinfosoft.touchstone.models;

import java.util.Date;
import java.util.Set;

public class Result {
	int resultId,userId,quizId;
	String date;
	float result,timeTaken;
	Set<QuizResult> quizResults;
	
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(int resultId, int userId, int quizId, String date,
			float result, float timeTaken, Set<QuizResult> quizResults) {
		this.resultId = resultId;
		this.userId = userId;
		this.quizId = quizId;
		this.date = date;
		this.result = result;
		this.timeTaken = timeTaken;
		this.quizResults = quizResults;
	}
	
	public Result(int resultId, int userId, int quizId, String date,
			float result, float timeTaken) {
		this.resultId = resultId;
		this.userId = userId;
		this.quizId = quizId;
		this.date = date;
		this.result = result;
		this.timeTaken = timeTaken;
	}

	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getResult() {
		return result;
	}
	public void setResult(float result) {
		this.result = result;
	}
	public float getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(float timeTaken) {
		this.timeTaken = timeTaken;
	}
	public Set<QuizResult> getQuizResults() {
		return quizResults;
	}
	public void setQuizResults(Set<QuizResult> quizResults) {
		this.quizResults = quizResults;
	}
	
}
