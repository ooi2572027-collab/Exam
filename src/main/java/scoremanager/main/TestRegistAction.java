package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        SubjectDao subjectDao   = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        StudentDao studentDao   = new StudentDao();
        TestDao testDao         = new TestDao();

        List<Subject> subjects    = subjectDao.filter(school);
        List<String> classNumList = classNumDao.filter(school);
        List<String> errors       = new ArrayList<>();

        // リクエストパラメーターの取得 2
        String subjectCd  = req.getParameter("subject_cd");
        String classNum   = req.getParameter("class_num");
        String noStr   = req.getParameter("no");
        String entYearStr = req.getParameter("ent_year");

        req.setAttribute("subjects",      subjects);
        req.setAttribute("class_num_set", classNumList);

        // Step1: 科目・クラス・回数が未選択 → 絞り込みフォームを表示
        if (subjectCd == null) {
            req.getRequestDispatcher("testregist_list.jsp").forward(req, res);
            return;
        }

        // Step2: 科目・クラス・回数が指定された → 学生一覧＋得点入力フォームを表示
        if (req.getParameter("point_0") == null) {
            // 学生一覧取得
            List<Student> students = null;
            if (entYearStr != null && !entYearStr.equals("0")) {
                students = studentDao.filter(school, Integer.parseInt(entYearStr), classNum, true);
            } else {
                students = studentDao.filter(school, classNum.equals("0") ? false : true);
                students = studentDao.filter(school, true);
            }

            // 入学年度とクラスで絞り込み
            if (entYearStr != null && !entYearStr.equals("0") && classNum != null && !classNum.equals("0")) {
                students = studentDao.filter(school, Integer.parseInt(entYearStr), classNum, true);
            } else if (entYearStr != null && !entYearStr.equals("0")) {
                students = studentDao.filter(school, Integer.parseInt(entYearStr), true);
            } else {
                students = studentDao.filter(school, true);
            }

            // 既存の得点を取得してセット
            List<Test> existingTests = new ArrayList<>();
            if (noStr != null && !noStr.isEmpty()) {
                int no = Integer.parseInt(noStr);
                for (Student s : students) {
                    Test t = testDao.get(s.getStudentNo(), subjectCd, school.getSchoolCd(), no);
                    existingTests.add(t); // nullの場合もある
                }
            }

            req.setAttribute("students",       students);
            req.setAttribute("existing_tests",  existingTests);
            req.setAttribute("subject_cd",      subjectCd);
            req.setAttribute("class_num",       classNum);
            req.setAttribute("no",           noStr);
            req.setAttribute("ent_year",        entYearStr);
            req.setAttribute("show_form",       true);
            req.getRequestDispatcher("testregist_list.jsp").forward(req, res);
            return;
        }

        // Step3: 得点を保存
        int no  = Integer.parseInt(noStr);

        // 学生番号のリストを取得
        String[] studentNos = req.getParameterValues("student_no");
        if (studentNos != null) {
            for (int i = 0; i < studentNos.length; i++) {
                String pointStr = req.getParameter("point_" + i);
                if (pointStr == null || pointStr.isEmpty()) continue;

                int point;
                try {
                    point = Integer.parseInt(pointStr);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (point < 0 || point > 100) continue;

                Student student = studentDao.get(studentNos[i]);
                if (student == null) continue;

                Test test = new Test();
                test.setStudentNo(studentNos[i]);
                test.setSubjectCd(subjectCd);
                test.setSchoolCd(school.getSchoolCd());
                test.setno(no);
                test.setpoint(point);
                test.setClassNum(student.getClassNum());

                testDao.save(test);
            }
        }

        req.getRequestDispatcher("testregist_done.jsp").forward(req, res);
    }
}
