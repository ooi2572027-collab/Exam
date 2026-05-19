package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        SubjectDao subjectDao   = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        TestDao testDao         = new TestDao();

        List<Subject> subjects    = subjectDao.filter(school);
        List<String> classNumList = classNumDao.filter(school);

        String subjectCd = req.getParameter("subject_cd");
        String classNum  = req.getParameter("class_num");
        String noStr     = req.getParameter("no");
        String studentNo = req.getParameter("student_no"); // 追加

        List<Test> tests = null;
        int no = 0;

        if (subjectCd != null || (studentNo != null && !studentNo.isEmpty())) {
            // 学生番号が入力されている場合はそちらを優先
            if (studentNo != null && !studentNo.isEmpty()) {
                tests = testDao.filterByStudent(school, studentNo);
            } else {
                if (noStr != null && !noStr.isEmpty() && !noStr.equals("0")) {
                    no = Integer.parseInt(noStr);
                }
                tests = testDao.filter(school, subjectCd, classNum, no);
            }
        }

        req.setAttribute("subjects",      subjects);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("tests",         tests);
        req.setAttribute("subject_cd",    subjectCd);
        req.setAttribute("class_num",     classNum);
        req.setAttribute("no",            noStr);
        req.setAttribute("student_no",    studentNo); // 追加

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}