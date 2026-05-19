package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String subjectCd = req.getParameter("cd");

        if (subjectCd == null || subjectCd.isEmpty()) {
            req.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp").forward(req, res);
            return;
        }

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        if (subject != null) {
            subjectDao.delete(subject);
        }

        req.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp").forward(req, res);
    }
}
