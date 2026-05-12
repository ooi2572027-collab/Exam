package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> errors = new ArrayList<>();

        String classNumStr    = req.getParameter("cd");
        String newClassNumStr = req.getParameter("new_class_num");

        if (newClassNumStr == null) {
            // GETアクセス：フォーム表示
            req.setAttribute("class_num", classNumStr);
            req.getRequestDispatcher("class_update.jsp").forward(req, res);
            return;
        }

        // POSTアクセス（更新処理）
        classNumStr    = req.getParameter("class_num");
        newClassNumStr = req.getParameter("new_class_num");

        if (newClassNumStr.isEmpty()) {
            errors.add("新しいクラス番号を入力してください");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("class_num", classNumStr);
            req.setAttribute("new_class_num", newClassNumStr);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("class_update.jsp").forward(req, res);
            return;
        }

        ClassNum classNum = classNumDao.get(classNumStr, school);
        if (classNum == null) {
            res.sendRedirect("ClassList.action");
            return;
        }

        classNumDao.save(classNum, newClassNumStr);

        res.sendRedirect("ClassList.action");
    }
}
