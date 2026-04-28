package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // リクエストパラメーターの取得 2
        String subjectCd = req.getParameter("cd");

        if (subjectCd == null || subjectCd.isEmpty()) {
            res.sendRedirect("SubjectList.action");
            return;
        }

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        if (subject != null) {
            subjectDao.delete(subject);
        }

        // 科目一覧へリダイレクト
        res.sendRedirect("SubjectList.action");
    }
}
