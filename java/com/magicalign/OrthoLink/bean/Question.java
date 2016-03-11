package com.magicalign.OrthoLink.bean;

public class Question {
	private String question;// 题目
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;

	private String optionE;
	private String optionF;
	private String optionG;
	private int answer;// 答案
	private int selectAnswer = 0;
	private String explain;

	
	public int getSelectAnswer() {
		return selectAnswer;
	}

	public void setSelectAnswer(int selectAnswer) {
		this.selectAnswer = selectAnswer;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	public String getOptionG() {
		return optionG;
	}

	public void setOptionG(String optionG) {
		this.optionG = optionG;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public Question() {
		super();
	}

	public Question(String question, String optionA, String optionB,
			String optionC, String optionD, String optionE, String optionF,
			String optionG, int answer) {
		super();
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.optionE = optionE;
		this.optionF = optionF;
		this.optionG = optionG;
		this.answer = answer;
	}

}
