<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

            <%-- 絞り込みフォーム --%>
            <div class="border rounded mx-4 mb-4 p-3 bg-light">
                <form method="get" class="row g-2 align-items-end">
                    <div class="col-md-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="subject_cd">
                            <option value="0">-- 全科目 --</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectCd}"
                                    <c:if test="${subject.subjectCd == subject_cd}">selected</c:if>>
                                    ${subject.subjectCd}：${subject.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="class_num">
                            <option value="0">-- 全クラス --</option>
                            <c:forEach var="cn" items="${class_num_set}">
                                <option value="${cn}" <c:if test="${cn == class_num}">selected</c:if>>${cn}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">回数</label>
                        <input type="number" class="form-control" name="no"
                               value="${no}" min="1" max="99" placeholder="全回数" />
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-secondary w-100">絞り込み</button>
                    </div>
                </form>
            </div>

            <%-- 結果テーブル --%>
            <c:if test="${tests != null}">
                <c:choose>
                    <c:when test="${not empty tests}">
                        <div class="px-4 mb-2">検索結果：${tests.size()}件</div>
                        <div class="px-4" style="overflow-x:auto;">
                            <table class="table table-hover table-sm table-bordered" style="width:auto; min-width:600px;">
                                <thead class="table-light">
                                    <tr>
                                        <th>科目コード</th>
                                        <th>科目名</th>
                                        <th>回数</th>
                                        <th>クラス</th>
                                        <th>学生番号</th>
                                        <th>氏名</th>
                                        <th class="text-end">得点</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="test" items="${tests}">
                                        <tr>
                                            <td>${test.subjectCd}</td>
                                            <td>${test.subject.subjectName}</td>
                                            <td class="text-center">${test.no}</td>
                                            <td>${test.classNum}</td>
                                            <td>${test.studentNo}</td>
                                            <td>${test.student.studentName}</td>
                                            <td class="text-end fw-bold
                                                <c:choose>
                                                    <c:when test="${test.point >= 80}">text-success</c:when>
                                                    <c:when test="${test.point < 60}">text-danger</c:when>
                                                    <c:otherwise>text-dark</c:otherwise>
                                                </c:choose>">
                                                ${test.point}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info mx-4">成績データが見つかりませんでした。</div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </section>
    </c:param>
</c:import>
