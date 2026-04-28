package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

	//04/28 11:30更新　動くかは知らん
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// ローカル変数の指定 1
		String subjectCd=null;
		List<Subject> subjects = null; // 科目リスト
		SubjectDao subjectDao = new SubjectDao(); // 科目Dao
		String school;
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメーターの取得 2

		// ビジネスロジック 4

		// DBからデータ取得 3
		// ログインユーザーの学校コードを取得
		school = teacher.getSchool().toString();

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
