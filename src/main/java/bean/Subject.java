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
}
