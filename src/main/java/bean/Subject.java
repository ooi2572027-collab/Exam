package bean;

import java.io.Serializable;

public class Subject extends User implements Serializable {

	private String subjectCd;
	private String subjectName;
	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * ゲッター・セッター
	 */
	
	public String getSubjectCd() {
		return subjectCd;
	}

	public void setSubjectCd(String scd) {
		this.subjectCd = scd;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String sname) {
		this.subjectName = sname;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
