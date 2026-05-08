<%-- 成績登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts">
        <script>
        $(function() {
            // 全員に同じ点数を一括入力するボタン
            $('#bulk-fill-btn').click(function() {
                var val = $('#bulk-score').val();
                if (val === '') return;
                $('input[name^="score_"]').val(val);
            });
        });
        </script>
    </c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録</h2>

            <%-- Step1：絞り込みフォーム --%>
            <div class="border rounded mx-4 mb-4 p-3 bg-light">
                <h5 class="mb-3">対象を選択</h5>
                <form method="get" class="row g-2 align-items-end">
                    <div class="col-md-3">
                        <label class="form-label">科目 <span class="text-danger">*</span></label>
                        <select class="form-select" name="subject_cd" required>
                            <option value="">-- 選択 --</option>
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
                        <label class="form-label">入学年度</label>
                        <input type="number" class="form-control" name="ent_year"
                               value="${ent_year}" placeholder="例：2023" min="2000" max="2099" />
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">回数 <span class="text-danger">*</span></label>
                        <input type="number" class="form-control" name="times"
                               value="${times}" min="1" max="99" required placeholder="例：1" />
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-secondary w-100">学生一覧を表示</button>
                    </div>
                </form>
            </div>

            <%-- Step2：得点入力フォーム --%>
            <c:if test="${show_form}">
                <c:choose>
                    <c:when test="${not empty students}">
                        <form method="post">
                            <input type="hidden" name="subject_cd" value="${subject_cd}" />
                            <input type="hidden" name="class_num"  value="${class_num}" />
                            <input type="hidden" name="times"      value="${times}" />
                            <input type="hidden" name="ent_year"   value="${ent_year}" />

                            <div class="mx-4 mb-3 d-flex align-items-center gap-2">
                                <span class="text-muted">一括入力：</span>
                                <input type="number" id="bulk-score" class="form-control"
                                       style="width:100px;" min="0" max="100" placeholder="点数" />
                                <button type="button" id="bulk-fill-btn" class="btn btn-outline-secondary btn-sm">
                                    全員に適用
                                </button>
                            </div>

                            <table class="table table-hover table-sm mx-4" style="width:auto; min-width:500px;">
                                <thead class="table-light">
                                    <tr>
                                        <th>学生番号</th>
                                        <th>氏名</th>
                                        <th>クラス</th>
                                        <th>得点（0〜100）</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="student" items="${students}" varStatus="st">
                                        <input type="hidden" name="student_no" value="${student.studentNo}" />
                                        <tr>
                                            <td>${student.studentNo}</td>
                                            <td>${student.studentName}</td>
                                            <td>${student.classNum}</td>
                                            <td>
                                                <input type="number" class="form-control form-control-sm"
                                                       name="score_${st.index}"
                                                       style="width:90px;"
                                                       min="0" max="100"
                                                       value="${existing_tests[st.index] != null ? existing_tests[st.index].score : ''}"
                                                       placeholder="未入力" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <div class="mx-4 mt-3">
                                <button type="submit" class="btn btn-primary me-2">保存</button>
                                <a href="TestRegist.action" class="btn btn-secondary">クリア</a>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning mx-4">対象の学生が見つかりませんでした。</div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </section>
    </c:param>
</c:import>
