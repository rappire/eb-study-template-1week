<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>글</title>
</head>
<body>
<header>
    <table>
        <tr>
            <td>작성자:</td>
            <td>${article.writer}</td>
            <td>등록일시:</td>
            <td>${article.regDate}</td>
            <td>수정일시:</td>
            <td>${article.editDate}</td>
        </tr>
        <tr>
            <td><h2>[ ${article.category} ]</h2></td>
            <td><h2>${article.title}</h2></td>
            <td>조회수:</td>
            <td>${article.views}</td>
        </tr>
    </table>
</header>
<main>
    <hr>
    <p style="border: 1px solid black; padding: 10px;">${article.contents}</p>
    <%-- 첨부 파일 --%>
    <c:if test="${article.fileCheck}">
        <a href="/download/${file.id}">${file.fileName}</a>
    </c:if>
    <div>
        <%-- 댓글 --%>
        <table>
            <thead>
            <tr>
                <th>작성시간</th>
                <th>내용</th>
            </tr>
            </thead>
            <c:if test="${not empty comments }">
                <c:forEach items="${ comments }" var="comment">
                    <tr text-align="center">
                        <td>${comment.writer }</td>
                        <td>${comment.contents }</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
            <form action="" method="post">
                <input type="text" name="contents" required>
                <input type="submit" value="작성">
            </form>
    </div>
</main>
<footer style="display: flex;">
    <form action="/boards/free/modify/${article.id}" method="get">
        <input type="submit" value="수정">
    </form>
    <form action="/boards/free/delete/${article.id}" method="get">
        <input type="submit" value="삭제">
    </form>
    <form action="/boards/free/list" method="get">
        <input type="submit" value="목록">
    </form>
</footer>

</body>
</html>
