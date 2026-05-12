package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * getメソッド 学生番号・科目コード・学校コード・回数で得点を1件取得する
     */
    public Test get(String studentNo, String subjectCd, String schoolCd, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from test where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
            statement.setString(1, studentNo);
            statement.setString(2, subjectCd);
            statement.setString(3, schoolCd);
            statement.setInt(4, no);
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setno(rSet.getInt("no"));
                test.setpoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { try { statement.close(); } catch (SQLException e) { throw e; } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { throw e; } }
        }
        return test;
    }

    /**
     * filterメソッド 学校・科目コード・クラス番号・回数で得点一覧を取得する
     */
    public List<Test> filter(School school, String subjectCd, String classNum, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            StringBuilder sql = new StringBuilder(
                "select t.*, s.student_name, sub.subject_name from test t " +
                "join student s on t.student_no = s.student_no and t.school_cd = s.school_cd " +
                "join subject sub on t.subject_cd = sub.subject_cd and t.school_cd = sub.school_cd " +
                "where t.school_cd = ?");

            List<Object> params = new ArrayList<>();
            params.add(school.getSchoolCd());

            if (subjectCd != null && !subjectCd.equals("0") && !subjectCd.isEmpty()) {
                sql.append(" and t.subject_cd = ?");
                params.add(subjectCd);
            }
            if (classNum != null && !classNum.equals("0") && !classNum.isEmpty()) {
                sql.append(" and t.class_num = ?");
                params.add(classNum);
            }
            if (no > 0) {
                sql.append(" and t.no = ?");
                params.add(no);
            }
            sql.append(" order by t.subject_cd, t.no, t.student_no");

            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                Object p = params.get(i);
                if (p instanceof String) statement.setString(i + 1, (String) p);
                else if (p instanceof Integer) statement.setInt(i + 1, (Integer) p);
            }

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setno(rSet.getInt("no"));
                test.setpoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));

                Student student = new Student();
                student.setStudentNo(rSet.getString("student_no"));
                student.setStudentName(rSet.getString("student_name"));
                student.setSchool(school);
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setSubjectCd(rSet.getString("subject_cd"));
                subject.setSubjectName(rSet.getString("subject_name"));
                subject.setSchool(school);
                test.setSubject(subject);

                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { try { statement.close(); } catch (SQLException e) { throw e; } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { throw e; } }
        }
        return list;
    }

    /**
     * filterメソッド 学校・学生番号で得点一覧を取得する（学生毎成績参照用）
     */
    public List<Test> filterByStudent(School school, String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select t.*, s.student_name, sub.subject_name from test t " +
                "join student s on t.student_no = s.student_no and t.school_cd = s.school_cd " +
                "join subject sub on t.subject_cd = sub.subject_cd and t.school_cd = sub.school_cd " +
                "where t.school_cd = ? and t.student_no = ? " +
                "order by t.subject_cd, t.no");
            statement.setString(1, school.getSchoolCd());
            statement.setString(2, studentNo);
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setno(rSet.getInt("no"));
                test.setpoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));

                Student student = new Student();
                student.setStudentNo(rSet.getString("student_no"));
                student.setStudentName(rSet.getString("student_name"));
                student.setSchool(school);
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setSubjectCd(rSet.getString("subject_cd"));
                subject.setSubjectName(rSet.getString("subject_name"));
                subject.setSchool(school);
                test.setSubject(subject);

                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { try { statement.close(); } catch (SQLException e) { throw e; } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { throw e; } }
        }
        return list;
    }

    /**
     * saveメソッド 得点を登録または更新する
     */
    public boolean save(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Test old = get(test.getStudentNo(), test.getSubjectCd(), test.getSchoolCd(), test.getno());
            if (old == null) {
                statement = connection.prepareStatement(
                    "insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?,?,?,?,?,?)");
                statement.setString(1, test.getStudentNo());
                statement.setString(2, test.getSubjectCd());
                statement.setString(3, test.getSchoolCd());
                statement.setInt(4, test.getno());
                statement.setInt(5, test.getpoint());
                statement.setString(6, test.getClassNum());
            } else {
                statement = connection.prepareStatement(
                    "update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
                statement.setInt(1, test.getpoint());
                statement.setString(2, test.getStudentNo());
                statement.setString(3, test.getSubjectCd());
                statement.setString(4, test.getSchoolCd());
                statement.setInt(5, test.getno());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { try { statement.close(); } catch (SQLException e) { throw e; } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { throw e; } }
        }
        return count > 0;
    }
    public void delete(String studentNo, String subjectCd, String schoolCd, int no) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "delete from test where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
            statement.setString(1, studentNo);
            statement.setString(2, subjectCd);
            statement.setString(3, schoolCd);
            statement.setInt(4, no);
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { try { statement.close(); } catch (SQLException e) { throw e; } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { throw e; } }
        }
    }
}
