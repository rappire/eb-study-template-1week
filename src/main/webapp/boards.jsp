<!-- listArticle.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
<div text-align="center">
    <header></header>
    <%--    <div>총 ${articlePage.count}</div>--%>
    <main>
        <table style="width: 600px;">
            <thead>
            <tr>
                <th>카테고리</th>
                <th>파일</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>등록일시</th>
                <th>수정일시</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty articlePage }">
                <tr text-align="center">
                    <td>등록된 게시글이 없습니다</td>
                </tr>
            </c:if>

            <c:if test="${not empty articlePage }">
                <c:forEach items="${ articlePage }" var="article">
                    <c:choose>
                        <c:when test="${article.fileCheck}">
                            <tr text-align="center">
                                <td>${article.kategorie }</td>
                                <td>
                                    <p>O</p>
                                </td>
                                <td>
                                    <a href="/boards/free/view/${article.id}">${article.title}</a>
                                </td>
                                <td>${article.writer }</td>
                                <td>${article.views }</td>
                                <td>${article.regDate }</td>
                                <td>${article.editDate }</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr text-align="center">
                                <td>${article.kategorie }</td>
                                <td>
                                    <p></p>
                                </td>
                                <td>
                                    <a href="/boards/free/view/${article.id}">${article.title}</a>
                                </td>
                                <td>${article.writer }</td>
                                <td>${article.views }</td>
                                <td>${article.regDate }</td>
                                <td>${article.editDate }</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
            </tbody>
            <%--        <tr>--%>
            <%--            <td colspan="5" align="center">--%>
            <%--                <div class="pagination">--%>
            <%--                    <c:if test="${ articlePage.pageBlock.prev }">--%>
            <%--                        <a href="/jspPro/article/list.do--%>
            <%--							?currentPage=${articlePage.pageBlock.start - 1 }--%>
            <%--							&searchCondition=${param.searchCondition}--%>
            <%--							&searchWord=${param.searchWord}">&laquo;</a>--%>
            <%--                    </c:if>--%>

            <%--                    <c:forEach begin="${articlePage.pageBlock.start }" end="${articlePage.pageBlock.end }" step="1" var="i">--%>
            <%--                        <c:if test="${i eq articlePage.pageBlock.curPage }">--%>
            <%--                            <a class="active" href="#">${i }</a>--%>
            <%--                        </c:if>--%>

            <%--                        <c:if test="${i ne articlePage.pageBlock.curPage }">--%>
            <%--                            <a href="/jspPro/article/list.do--%>
            <%--								?currentPage=${i }--%>
            <%--								&searchCondition=${param.searchCondition}--%>
            <%--								&searchWord=${param.searchWord}">${i }</a>--%>
            <%--                        </c:if>--%>
            <%--                    </c:forEach>--%>
            <%--                    <c:if test="${articlePage.pageBlock.next }">--%>
            <%--                        <a href="/jspPro/article/list.do--%>
            <%--							?currentPage=${articlePage.pageBlock.end + 1 }--%>
            <%--							&searchCondition=${param.searchCondition}--%>
            <%--							&searchWord=${param.searchWord}">&raquo;</a>--%>
            <%--                    </c:if>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--        </tr>--%>
            <%--        <tr>--%>
            <%--            <td colspan="5" align="center">--%>
            <%--                <form action="" method="get">--%>
            <%--                    <select name="searchCondition" id="searchCondition">--%>
            <%--                        <option value="1">제목</option>--%>
            <%--                        <option value="2">내용</option>--%>
            <%--                        <option value="3">글쓴이</option>--%>
            <%--                        <option value="4">제목+내용</option>--%>
            <%--                    </select>--%>
            <%--                    <input type="text" name="searchWord" id="searchWord" value="${param.searchWord}"/>--%>
            <%--                    <input type="submit" value="검색"/>--%>
            <%--                    &lt;%&ndash; <input type="hidden" name="currentPage" value="${param.currentPage }"/> &ndash;%&gt;--%>
            <%--                </form>--%>
            <%--            </td>--%>
            <%--        </tr>--%>
            <%--        <tr>--%>
            <%--            <td colspan="5" text-align="center">--%>
            <%--                <a href="/jspPro/index.do" style="float: left;">홈</a>--%>
            <%--                <a href="/jspPro/article/write.do">글쓰기</a>--%>
            <%--            </td>--%>
            <%--        </tr>--%>
            <%--        </tfoot>--%>
        </table>
    </main>
    <footer style="display: flex;">
        <form action="/boards/free/write" method="get">
            <input type="submit" value="등록">
        </form>
    </footer>
</div>
<script>
</script>
</body>
</html>