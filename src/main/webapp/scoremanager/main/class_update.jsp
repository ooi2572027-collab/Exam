<%-- クラス変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス変更</h2>
            <div class="my-2 px-4">
                <a href="ClassList.action">&laquo; クラス一覧へ戻る</a>
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
                <input type="hidden" name="class_num" value="${class_num}" />
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">現在のクラス番号</label>
                    <div class="col-sm-4">
                        <p class="form-control-plaintext fw-bold">${class_num}</p>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">新しいクラス番号 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="new_class_num"
                               value="${new_class_num}" maxlength="10" required />
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-9 offset-sm-3 text-muted small">
                        ※ クラス番号を変更すると、関連する学生・成績データのクラス番号も更新されます。
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-primary me-2">更新</button>
                        <a href="ClassList.action" class="btn btn-secondary">キャンセル</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
