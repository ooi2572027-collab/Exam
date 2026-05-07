package bean;

import java.io.Serializable;

/**
 * 科目情報を表す Bean クラス
 */
public class Subject implements Serializable {

    /** 学校コード */
    private String schoolCd;

    /** 科目コード */
    private String subjectCd;

    /** 科目名 */
    private String subjectName;

    /** 所属学校 */
    private School school;

    // --- getter / setter ---

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
=======
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
>>>>>>> branch 'master' of https://github.com/ooi2572027-collab/Exam.git
}
