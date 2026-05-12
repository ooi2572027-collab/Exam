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

public class ClassCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String classNumStr  = req.getParameter("class_num");
        List<String> errors = new ArrayList<>();
        ClassNumDao classNumDao = new ClassNumDao();

        if (classNumStr == null) {
            // GETアクセス：入力フォームを表示
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        // バリデーション
        if (classNumStr.isEmpty()) {
            errors.add("クラス番号を入力してください");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("class_num", classNumStr);
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        // 重複チェック
        ClassNum existing = classNumDao.get(classNumStr, school);
        if (existing != null) {
            errors.add("このクラス番号はすでに登録されています");
            req.setAttribute("errors", errors);
            req.setAttribute("class_num", classNumStr);
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        ClassNum classNum = new ClassNum();
        classNum.setClassNum(classNumStr);
        classNum.setSchool(school);

        // DBへデータ保存 5
        classNumDao.save(classNum);

        // クラス一覧へリダイレクト
        res.sendRedirect("ClassList.action");
    }
}
