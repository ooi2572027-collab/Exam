<%-- エラーページ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - エラー</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4 text-danger">エラーが発生しました</h2>
            <div class="mx-4 mt-3">
                <p>申し訳ございません。処理中にエラーが発生しました。</p>
                <p>しばらく時間をおいてから再度お試しください。</p>
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/scoremanager/login.jsp" class="btn btn-primary">
                        ログインページへ戻る
                    </a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>