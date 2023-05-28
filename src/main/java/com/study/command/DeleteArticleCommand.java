package com.study.command;

import static com.study.utile.CheckInput.isLong;

import com.study.dao.ArticleDao;
import com.study.utile.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteArticleCommand
 * 삭제페이지 및 삭제를 수행하는 command
 */
public class DeleteArticleCommand implements Command {

    /**
     * articleDao   : ArticleDao 를 싱글톤 패턴으로 생성해서 호출
     */
    private ArticleDao articleDao = ArticleDao.getInstance();

    /**
     * execute
     * @param request : HttpServletRequest
     * @param response : HttpServletResponse
     * 요청을 받아서, 만약 seq 가 long 이 아닐경우 잘못된 경로를 참조한것이니, /boards/free/list 로 이동
     * 1. GET
     *    delete 페이지를 리턴
     * 2. POST
     *    패스워드 확인 후 삭제, 삭제후 /boards/free/list 로 이동
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        String seq = uri.substring(index + 1);
        if (!isLong(seq)) {
            request.getRequestDispatcher("/boards/free/list").forward(request, response);
            // 에러 발생
            System.out.println("에러");
            return;
        }
        Long id = Long.parseLong(seq);
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher("/delete.jsp").forward(request, response);
        }
        else if (request.getMethod().equals("POST")) {
        //            if  (password is correct)
        //            request.getParameter("password");
            articleDao.deleteArticle(id);
            request.getRequestDispatcher("/boards/free/list").forward(request, response);
        }
    }
}
