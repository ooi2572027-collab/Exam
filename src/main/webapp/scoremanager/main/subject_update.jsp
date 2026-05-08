<%-- 科目変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
            <div class="my-2 px-4">
                <a href="SubjectList.action">&laquo; 科目一覧へ戻る</a>
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

            <form method="post" action="SubjectUpdateExecute.action" class="mx-4 mt-3">
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">科目コード</label>
                    <div class="col-sm-4">
                        <p class="form-control-plaintext">${subject.subjectCd}</p>
                        <input type="hidden" name="subject_cd" value="${subject.subjectCd}">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">科目名 <span class="text-danger">*</span></label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="subject_name"
                               value="${subject.subjectName}" maxlength="50" required />
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-primary me-2">更新</button>
                        <a href="SubjectList.action" class="btn btn-secondary">キャンセル</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
