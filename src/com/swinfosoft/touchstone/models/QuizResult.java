package com.swinfosoft.touchstone.models;

public class QuizResult {
	int qrId,resultId,questionId,answer;
	float timeTaken;
	
	
	public QuizResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuizResult(int qrId, int resultId, int questionId, int answer,
			float timeTaken) {
		this.qrId = qrId;
		this.resultId = resultId;
		this.questionId = questionId;
		this.answer = answer;
		this.timeTaken = timeTaken;
	}
	public int getQrId() {
		return qrId;
	}
	public void setQrId(int qrId) {
		this.qrId = qrId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public float getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(float timeTaken) {
		this.timeTaken = timeTaken;
	}
	
}
