package ustc.sse.sa16225300.domain;

import java.io.Serializable;

public class GradeTable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String studentName;
	private String studentID;
	private String score;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}	
}
