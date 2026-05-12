package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String studentNo = req.getParameter("student_no");
        String subjectCd = req.getParameter("subject_cd");
        String noStr     = req.getParameter("no");

        if (studentNo != null && subjectCd != null && noStr != null && !noStr.isEmpty()) {
            int no = Integer.parseInt(noStr);
            TestDao testDao = new TestDao();
            testDao.delete(studentNo, subjectCd, school.getSchoolCd(), no);
        }

        res.sendRedirect("TestList.action");
    }
}