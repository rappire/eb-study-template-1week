package com.study.utile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.util.regex.Pattern;

public class CheckInput {

    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

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

    public static boolean checkPassword(String password) {
        String pattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{4,15}$";
        return Pattern.matches(pattern, password);
    }

    public static boolean checkRequest(HttpServletRequest request) {
        String kategorie = request.getParameter("kategorie");
        String writer = request.getParameter("writer");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        return checkRequest(kategorie, writer, password, confirmPassword, title, contents);
    }

    public static boolean checkRequest(HttpServletRequest request, String confirmPassword) {
        String writer = request.getParameter("writer");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        return checkRequest(writer, password, confirmPassword, title, contents);
    }

    private static boolean checkRequest(String kategorie, String writer, String password,
        String confirmPassword, String title, String contents) {
        if (kategorie == null) {
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

    private static boolean checkRequest(String writer, String password,
        String confirmPassword, String title, String contents) {
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
