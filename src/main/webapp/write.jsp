<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>등록</title>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
    <label>카테고리:</label>
    <select name="category">
        <c:forEach items="${categoryList}" var="category">
            <option value="${category.id}"> ${category.category}</option>
        </c:forEach>
    </select>
    <br>
    <label for="writer">작성자:</label>
    <input type="text" name="writer" id="writer" required>
    <br>
    <label for="password">비밀번호:</label>
    <input type="password" name="password" id="password" placeholder="비밀번호" required>
    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="비밀번호 확인" required>
    <br>
    <label for="title">제목:</label>
    <input type="text" name="title" id="title" required>
    <br>
    <p>내용:</p>
    <textarea name="contents" required></textarea>
    <br>
    <label for="file">첨부 파일:</label>
    <input type="file" name="file" id="file">
    <br>
    <input type="submit" value="저장">
    <input type="button" value="취소" onclick="location.href='/boards/free/list'">
</form>
<c:if test="${not empty message}">
    <script>
      alert('${message}');
    </script>
</c:if>
</body>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
    }

    h1 {
        margin-bottom: 20px;
    }

    form {
        margin-bottom: 20px;
    }

    /* 입력 필드 스타일 */
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    /*input[type="text"]*/
    textarea {
        width: 600px;
        height: 500px;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    input[type="file"] {
        margin-top: 5px;
    }

    input[type="submit"],
    input[type="button"] {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
</html>
