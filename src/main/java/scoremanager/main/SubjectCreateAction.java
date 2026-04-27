package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		// ローカル変数の指定 1
		ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化

		// リクエストパラメーターの取得 2
		// なし

		// DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = classNumDao.filter(teacher.getSchool());

		// ビジネスロジック 4
		// リストを初期化

		// レスポンス値をセット 6
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}

}
