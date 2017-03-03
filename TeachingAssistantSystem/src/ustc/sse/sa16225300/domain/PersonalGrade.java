package ustc.sse.sa16225300.domain;

import java.io.Serializable;

public class PersonalGrade implements Serializable {
	private static final long serialVersionUID = 1L;

	private String studentID;
	private String homework;
	private String report;
	private String interaction;
	private String middleTest;
	private String finalTest;
	private String finalScore;
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getHomework() {
		return homework;
	}
	public void setHomework(String homework) {
		this.homework = homework;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getInteraction() {
		return interaction;
	}
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	public String getMiddleTest() {
		return middleTest;
	}
	public void setMiddleTest(String middleTest) {
		this.middleTest = middleTest;
	}
	public String getFinalTest() {
		return finalTest;
	}
	public void setFinalTest(String finalTest) {
		this.finalTest = finalTest;
	}
	public String getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}
}
