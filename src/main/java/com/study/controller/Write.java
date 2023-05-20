package com.study.controller;

import com.study.dao.ArticleDao;
import com.study.dao.FileDao;
import com.study.dto.ArticleDTO;
import com.study.dto.FileDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

@WebServlet(name = "write", value = "/boards/free/write")
@MultipartConfig(maxFileSize = 16177215)
public class Write extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String path = "/write.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 인풋 검사
        if (!checkRequest(request)) {
            System.out.println("실패");
            return;
        }
        // 파일 처리
//        Part filePart = request.getPart("file");
//        String fileName = getFileName(filePart);
//        System.out.println(fileName);
//        String applicationPath = request.getServletContext().getRealPath("");
//        String uploadFilePath = applicationPath + File.separator + "uploads";
//        System.out.println(applicationPath + File.separator + "uploads");
//        File fileSaveDir = new File(uploadFilePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs();
//        }
//        if (!fileName.isEmpty()) {
//            filePart.write(uploadFilePath + File.separator + fileName);
//        }

        InputStream inputStream = null;
        Part filePart = request.getPart("file");
        boolean filecheck = false;
        if (filePart != null) {
            inputStream = filePart.getInputStream();
            if (inputStream != null) {
                filecheck = true;
            }
        }

        ArticleDao articleDao = ArticleDao.getInstance();
        ArticleDTO article = new ArticleDTO();
        article.setKategorie(request.getParameter("kategorie"));
        article.setTitle(request.getParameter("title"));
        article.setWriter(request.getParameter("writer"));
        article.setPassword(request.getParameter("password"));
        article.setContents(request.getParameter("contents"));
        article.setViews(0);
        article.setFileCheck(filecheck);
        long id = articleDao.postArticle(article);
        System.out.println(id);

        if (inputStream != null && id != -1) {
            FileDao fileDao = FileDao.getInstance();
            FileDTO file = new FileDTO();
            file.setFileName(getFileName(filePart));
            file.setArticleId(id);
            file.setFile(inputStream);
            fileDao.postFile(file);
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    public boolean checkPassword(String password) {
        String pattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{4,15}$";
        return Pattern.matches(pattern, password);
    }

    public boolean checkRequest(HttpServletRequest request) {
        String kategorie = request.getParameter("kategorie");
        String writer = request.getParameter("writer");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        return checkRequest(kategorie, writer, password, confirmPassword, title, contents);
    }

    private boolean checkRequest(String kategorie, String writer, String password,
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
}
