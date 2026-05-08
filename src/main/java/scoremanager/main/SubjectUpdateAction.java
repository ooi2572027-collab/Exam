package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
	
		// ローカル変数の指定 1
		ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化
		String subjectCd=null;
		String schoolCd=null;
		Subject subject = null;
		School school = null;
		
		SubjectDao subjectDao = new SubjectDao();
		
		school = teacher.getSchool();

		// リクエストパラメーターの取得 2
		subjectCd = req.getParameter("cd");
		schoolCd = req.getParameter("school");

		// DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = classNumDao.filter(teacher.getSchool());
		subject = subjectDao.get(subjectCd,school);

		// ビジネスロジック 4
		// リストを初期化

		// レスポンス値をセット 6
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("subject", subject);
		req.setAttribute("school", schoolCd);
		req.setAttribute("cd", subjectCd);

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}

}
