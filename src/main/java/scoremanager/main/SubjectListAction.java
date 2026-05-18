package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// ローカル変数の指定 1
		List<Subject> subjects = null; // 科目リスト
		SubjectDao subjectDao = new SubjectDao(); // 科目Dao
		School school;

		// リクエストパラメーターの取得 2

		// ビジネスロジック 4

		// DBからデータ取得 3
		// ログインユーザーの学校コードを取得
		school = teacher.getSchool();

		subjects = subjectDao.filter(teacher.getSchool());

		// レスポンス値をセット 6

		// リクエストに科目リストをセット
		req.setAttribute("subjects", subjects);
		// リクエストにデータをセット
		req.setAttribute("school", school);

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}

}
