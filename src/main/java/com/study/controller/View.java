package com.study.controller;

import static com.study.utile.CheckInput.isLong;

import com.study.dao.ArticleDao;
import com.study.dao.FileDao;
import com.study.dto.ArticleDTO;
import com.study.dto.FileDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "view", value = "/boards/free/view/*")
public class View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 경로 확인
        String seq = request.getPathInfo().substring(1);
        if (!isLong(seq)) {
            // 에러 발생
            return;
        }
        Long id = Long.parseLong(seq);
        //
        ArticleDao articleDao = ArticleDao.getInstance();
        ArticleDTO article = articleDao.getArticle(id);
        FileDTO file = null;
        if (article.getFileCheck()) {
            FileDao fileDao = FileDao.getInstance();
            file = fileDao.getFileByArticle(id);
        }
        String path = "/view.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        request.setAttribute("article", article);
        request.setAttribute("file", file);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }


}
