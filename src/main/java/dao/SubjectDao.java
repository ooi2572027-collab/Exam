package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * getメソッド 学校コードと科目コードで科目インスタンスを1件取得する
     *
     * @param subjectCd 科目コード
     * @param school    所属学校
     * @return 科目インスタンス（存在しない場合はnull）
     * @throws Exception
     */
    public Subject get(String subjectCd, School school) throws Exception {
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where subject_cd = ? and school_cd = ?");
            statement.setString(1, subjectCd);
            statement.setString(2, school.getSchoolCd());
            ResultSet rSet = statement.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
                subject.setSubjectCd(rSet.getString("subject_cd"));
                subject.setSubjectName(rSet.getString("subject_name"));
                subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                subject = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return subject;
    }

    /**
     * filterメソッド 学校を指定して科目一覧を取得する
     *
     * @param school 所属学校
     * @return 科目リスト
     * @throws Exception
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where school_cd = ? order by subject_cd");
            statement.setString(1, school.getSchoolCd());
            ResultSet rSet = statement.executeQuery();

            SchoolDao schoolDao = new SchoolDao();
            School s = schoolDao.get(school.getSchoolCd());

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectCd(rSet.getString("subject_cd"));
                subject.setSubjectName(rSet.getString("subject_name"));
                subject.setSchool(s);
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return list;
    }

    /**
     * saveメソッド 科目を登録または更新する
     *
     * @param subject 科目インスタンス
     * @return 成否
     * @throws Exception
     */
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Subject old = get(subject.getSubjectCd(), subject.getSchool());
            if (old == null) {
                // 新規登録
                statement = connection.prepareStatement(
                    "insert into subject(school_cd, subject_cd, subject_name) values(?, ?, ?)");
                statement.setString(1, subject.getSchool().getSchoolCd());
                statement.setString(2, subject.getSubjectCd());
                statement.setString(3, subject.getSubjectName());
            } else {
                // 更新
                statement = connection.prepareStatement(
                    "update subject set subject_name = ? where school_cd = ? and subject_cd = ?");
                statement.setString(1, subject.getSubjectName());
                statement.setString(2, subject.getSchool().getSchoolCd());
                statement.setString(3, subject.getSubjectCd());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return count > 0;
    }

    /**
     * deleteメソッド 科目を削除する
     *
     * @param subject 科目インスタンス
     * @return 成否
     * @throws Exception
     */
    public boolean delete(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "delete from subject where school_cd = ? and subject_cd = ?");
            statement.setString(1, subject.getSchool().getSchoolCd());
            statement.setString(2, subject.getSubjectCd());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return count > 0;
    }
}
