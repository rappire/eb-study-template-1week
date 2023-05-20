package com.study.controller;

import com.study.dao.ArticleDao;
import com.study.dto.ArticleDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "boards", value = "/boards/free/list")
public class Boards extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 인풋 처리
        ArticleDao dao = ArticleDao.getInstance();
        List<ArticleDTO> list = dao.getArticleList();
        //
        String path = "/boards.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        request.setAttribute("articlePage", list);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}
