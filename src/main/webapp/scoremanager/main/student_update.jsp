<%-- 学生変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生変更</h2>
            <div class="my-2 px-4">
                <a href="StudentList.action">&laquo; 学生一覧へ戻る</a>
            </div>

            <c:if test="${not empty errors}">
                <div class="alert alert-danger mx-4">
                    <ul class="mb-0">
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="post" class="mx-4 mt-3">
                <input type="hidden" name="student_no" value="${student.studentNo}" />
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">学生番号</label>
                    <div class="col-sm-6">
                        <p class="form-control-plaintext">${student.studentNo}</p>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">氏名 <span class="text-danger">*</span></label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="student_name"
                               value="${student.studentName}" maxlength="50" required />
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">入学年度 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="ent_year"
                               value="${student.entYear}" min="2000" max="2099" required />
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">クラス <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <select class="form-select" name="class_num" required>
                            <option value="0">-- 選択してください --</option>
                            <c:forEach var="cn" items="${class_num_set}">
                                <option value="${cn}" <c:if test="${cn == student.classNum}">selected</c:if>>${cn}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">在学中</label>
                    <div class="col-sm-4 d-flex align-items-center">
                        <input class="form-check-input" type="checkbox" name="is_attend" value="t"
                               <c:if test="${student.isAttend()}">checked</c:if> />
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-primary me-2">更新</button>
                        <a href="StudentList.action" class="btn btn-secondary">キャンセル</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
