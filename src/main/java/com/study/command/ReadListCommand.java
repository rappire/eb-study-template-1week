package com.study.command;

import com.study.dao.ArticleDao;
import com.study.dto.ArticleDTO;
import com.study.utile.Command;
import com.study.utile.Connector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.HttpSession;

/**
 * ReadListCommand
 * 글 목록을 보여줄때 사용하는 command
 */
public class ReadListCommand implements Command {
    /**
     * articleDao   : ArticleDao 를 싱글톤 패턴으로 생성해서 호출
     */
    private ArticleDao articleDao = ArticleDao.getInstance();
    /**
     * execute
     * @param request : HttpServletRequest
     * @param response : HttpServletResponse
     * 요청을 받아서 키워드가 존재하는지 확인하고,
     * 1. 키워드가 NULL 일 경우에는
     * 세션에서 가져와서 해당 키워드로 검색
     * 2. 키워그가 ""일 경우에는
     * 키워드 없이 전체 글을 보여줌
     * 3. 1,2에 해당하지 않을 경우에는
     * 키워드를 세션에 저장하고 키워드로 검색
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        String searchKeyword = request.getParameter("keyword");
        if(searchKeyword == null){
            searchKeyword = (String)session.getAttribute("searchKeyword");
        }
        else if (searchKeyword.equals("")) {
            searchKeyword = null;
        }else{
            session.setAttribute("searchKeyword", searchKeyword);
        }
        List<ArticleDTO> list = articleDao.getArticleList(searchKeyword);
        request.setAttribute("articlePage", list);
        request.getRequestDispatcher("/boards.jsp").forward(request, response);

    }
}
