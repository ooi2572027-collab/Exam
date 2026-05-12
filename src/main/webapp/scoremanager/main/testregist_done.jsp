<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <%-- titleという名前でブラウザのタイトルを渡す --%>
    <c:param name="title">成績管理システム</c:param>

    <%-- contentという名前で画面の本体部分を渡す --%>
    <c:param name="content">
        <div class="container">
            <h2 class="h3 mb-3 fw-normal">成績管理システム</h2>
            <div class="message alert alert-success">
                登録が完了しました
            </div>
            <div class="links mt-3">
                <a href="TestRegist.action" class="btn btn-primary">戻る</a>
                <a href="TestList.action" class="btn btn-secondary">成績参照</a>
            </div>
        </div>
    </c:param>
</c:import>