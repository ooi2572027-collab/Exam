<%-- クラス一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            <div class="my-2 text-end px-4">
                <a href="ClassCreate.action">新規登録</a>
            </div>

            <c:choose>
                <c:when test="${not empty class_num_set}">
                    <div class="px-4">${class_num_set.size()}件</div>
                    <table class="table table-hover mx-4" style="width:auto;">
                        <tr>
                            <th>クラス番号</th>
                            <th></th>
                        </tr>
                        <c:forEach var="cn" items="${class_num_set}">
                            <tr>
                                <td>${cn}</td>
                                <td><a href="ClassUpdate.action?cd=${cn}">変更</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="px-4">クラス情報が存在しませんでした。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
