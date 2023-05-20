<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정</title>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
    <table>
        <td>
        <td>카테고리: ${article.kategorie}</td>
        <td>등록일시:</td>
        <td>${article.regDate}</td>
        <td>수정일시:</td>
        <td>${article.editDate}</td>
        <td>
            <label for="writer">작성자:</label>
            <input type="text" name="writer" id="writer" value="${article.writer}" required>
        </td>
        <td>
            <label for="title">제목:</label>
            <input type="text" name="title" id="title" value="${article.title}" required>
        </td>
        <td>
            <label for="password">비밀번호:</label>
            <input type="password" name="password" id="password" value="${article.password}"
                   placeholder="비밀번호"
                   required>
        </td>
        <td>
            <p>내용:</p>
            <textarea name="contents" required>${article.contents}</textarea>
        </td>
        <td>
            <c:choose>
                <c:when test="${article.fileCheck}">
                    <a href="/download/${file.id}">${file.fileName}</a>
                </c:when>
                <c:otherwise>
                    <label for="file">첨부 파일: <input type="file" name="file" id="file"></label>
                </c:otherwise>
            </c:choose>
        </td>
        </tr>
    </table>
    <input type="submit" value="수정">
    <input type="button" value="취소" onclick="location.href='/boards/free/list/'">
</form>
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

  td {
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
