package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		// ローカル変数の指定 1
		String subjectCd = "";
		String subjectName = ""; // 入力された教科名
		Subject subject = new Subject();
		SubjectDao subjectDao = new SubjectDao();
		String errors = null; // エラーメッセージ
		School school = teacher.getSchool();

		// リクエストパラメーターの取得 2
		subjectCd = req.getParameter("subject_cd");
		subjectName = req.getParameter("subject_name");
		subject = subjectDao.get(subjectCd,school);

		// DBからデータ取得 3
		if(subject == null) {
			errors = "科目が存在していません";
		}else {
			subject.setSubjectName(subjectName);
			// saveメソッドで情報を登録
			subjectDao.save(subject);
		}

		// レスポンス値をセット 6
		req.setAttribute("subjectCd", subjectCd);
		req.setAttribute("subjectName", subjectName);

		// JSPへフォワード 7
		if (errors==null) { // エラーメッセージがない場合
			// 登録完了画面にフォワード
			req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
		} else { // エラーメッセージがある場合
			// 登録画面にフォワード
			req.getRequestDispatcher("SubjectUpdate.action").forward(req, res);
		}
	}

}
