package bean;

import java.io.Serializable;

/**
 * 得点情報を表す Bean クラス
 */
public class Test implements Serializable {

    /** 学生番号 */
    private String studentNo;

    /** 科目コード */
    private String subjectCd;

    /** 学校コード */
    private String schoolCd;

    /** 回数 */
    private int no ;

    /** 得点 */
    private int point ;

    /** クラス番号 */
    private String classNum;

    /** 学生インスタンス */
    private Student student;

    /** 科目インスタンス */
    private Subject subject;

    // --- getter / setter ---

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public int getno() {
        return no;
    }

    public void setno(int no) {
        this.no = no;
    }

    public int getpoint() {
        return point;
    }

    public void setpoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
