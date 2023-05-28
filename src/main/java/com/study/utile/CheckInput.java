package com.study.utile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.util.regex.Pattern;

/**
 * CheckInput
 * 입력 체크와 관련된 함수를 모은 객체
 */
public class CheckInput {

    /**
     * isLong.
     *
     * @param value 확인할 String
     * @return value 가 Long 인지 판별한 결과
     * value 가 Long 인지 판별해주는 함수
     */
    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * getFileName
     *
     * @param part 파일 이름을 추출할 part
     * @return 추출한 파일 이름
     * 파일 이름을 추출해서 반환
     */
    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    /**
     * checkPassword
     *
     * @param password 확인할 패스워드
     * @return 패스워드가 조건에 맞는지 판별한 결과
     * 패스워드가 조건을 만족하는지 판별하는 함수
     */
    public static boolean checkPassword(String password) {
        String pattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{4,15}$";
        return Pattern.matches(pattern, password);
    }

    /**
     * checkRequest
     * @param request 글 생성이 맞게 되는지 확인할 request
     * @return 글이 양식에 맞는지 판별한 결과
     * 글이 양식에 맞는지 판별하는 함수
     */
    public static boolean checkRequest(HttpServletRequest request) {
        String category = request.getParameter("category");
        String writer = request.getParameter("writer");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        if (category == null) {
            return false;
        }
        if (writer == null) {
            return false;
        } else if (writer.length() < 3 || writer.length() >= 5) {
            return false;
        }
        if (password == null || password.compareTo(confirmPassword) != 0) {
            return false;
        } else if (!checkPassword(password)) {
            return false;
        }
        if (title == null) {
            return false;
        } else if (title.length() < 4 || title.length() >= 100) {
            return false;
        }
        if (contents == null) {
            return false;
        } else if (contents.length() < 4 || contents.length() >= 2000) {
            return false;
        }
        return true;
    }

    /**
     * Check request boolean.
     *
     * @param request 글 수정이 맞게 되는지 확인할 request
     * @param confirmPassword 수정할 글의 있는 password
     * @return 글 수정이 가능한지 판별한 결과
     * 글이 양식에 맞게 수정되었는지, 비밀번호가 맞는지 판별하는 함수
     */
    public static boolean checkRequest(HttpServletRequest request, String confirmPassword) {
        String writer = request.getParameter("writer");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        if (writer == null) {
            return false;
        } else if (writer.length() < 3 || writer.length() >= 5) {
            return false;
        }
        if (password == null || password.compareTo(confirmPassword) != 0) {
            return false;
        } else if (!checkPassword(password)) {
            return false;
        }
        if (title == null) {
            return false;
        } else if (title.length() < 4 || title.length() >= 100) {
            return false;
        }
        if (contents == null) {
            return false;
        } else if (contents.length() < 4 || contents.length() >= 2000) {
            return false;
        }
        return true;
    }

}
