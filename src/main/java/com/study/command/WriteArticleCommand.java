package com.study.command;

import static com.study.utile.CheckInput.checkRequest;

import com.study.dao.ArticleDao;
import com.study.dao.CategoryDao;
import com.study.dao.CommentDao;
import com.study.dto.ArticleDTO;
import com.study.dto.CategoryDTO;
import com.study.utile.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * WriteArticleCommand
 * 글을 작성하는 페이지를 보여주고, 글을 작성할때 사용하는 command
 */
public class WriteArticleCommand implements Command {
    /**
     * articleDao   : ArticleDao 를 싱글톤 패턴으로 생성해서 호출
     * commentDao   : CommentDao 를 싱글톤 패턴으로 생성해서 호출
     * categoryDao  : CategoryDao 를 싱글톤 패턴으로 생성해서 호출
     */
    private ArticleDao articleDao = ArticleDao.getInstance();
    private CommentDao commentDao  = CommentDao.getInstance();
    private CategoryDao categoryDao = CategoryDao.getInstance();
    /**
     * execute
     * @param request : HttpServletRequest
     * @param response : HttpServletResponse
     * 요청을 받아서 키워드가 존재하는지 확인하고,
     * 요청을 받아서,
     * 1. GET 
     *    글 쓰기 페이지로 이동
     * 2. POST 
     *    a. 만약 seq 가 long 이 아닐경우 잘못된 경로를 참조한것이니, /boards/free/list 로 이동
     *    b. 글의 형식을 확인하고 맞지 않을경우 에러 메세지를 띄움
     *    c. a,b에 해당하지 않을 경우 글을 작성하고 /boards/free/list 로 이동
     * 
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            List<CategoryDTO> category = categoryDao.getCategoryList();
            request.setAttribute("categoryList", category);
            request.getRequestDispatcher("/write.jsp").forward(request, response);

        } else if (request.getMethod().equals("POST")) {
            if (!checkRequest(request)) {
                List<CategoryDTO> category = categoryDao.getCategoryList();
                request.setAttribute("categoryList", category);
                request.setAttribute("message", "형식에 맞지 않습니다.");
                request.getRequestDispatcher("/write.jsp").forward(request, response);
                return;
            }
            ArticleDTO article = new ArticleDTO();
            article.setCategory_id(Long.parseLong(request.getParameter("category")));
            article.setTitle(request.getParameter("title"));
            article.setWriter(request.getParameter("writer"));
            article.setPassword(request.getParameter("password"));
            article.setContents(request.getParameter("contents"));
            article.setViews(0);
            long id = articleDao.postArticle(article);
            request.getRequestDispatcher("/boards/free/list").forward(request, response);
        }
    }
}
