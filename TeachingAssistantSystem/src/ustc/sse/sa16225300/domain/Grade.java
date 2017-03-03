package ustc.sse.sa16225300.domain;

import java.io.Serializable;

public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentID;
    private String gradeType;
    private String score;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getGradeType() {
		return gradeType;
	}
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
        
}
