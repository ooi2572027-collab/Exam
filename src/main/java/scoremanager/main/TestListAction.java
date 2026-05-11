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

        // リクエストパラメーターの取得 2
        String subjectCd = req.getParameter("subject_cd");
        String classNum  = req.getParameter("class_num");
        String timesStr  = req.getParameter("times");

        List<Test> tests = null;
        int times = 0;

        if (subjectCd != null) {
            // 絞り込み検索
            if (timesStr != null && !timesStr.isEmpty() && !timesStr.equals("0")) {
                times = Integer.parseInt(timesStr);
            }
            tests = testDao.filter(school, subjectCd, classNum, times);
        }

        // レスポンス値をセット 6
        req.setAttribute("subjects",      subjects);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("tests",         tests);
        req.setAttribute("subject_cd",    subjectCd);
        req.setAttribute("class_num",     classNum);
        req.setAttribute("times",         timesStr);

        // JSPへフォワード 7
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
