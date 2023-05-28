package com.study.command;

import static com.study.utile.CheckInput.isLong;

import com.study.dao.ArticleDao;
import com.study.dao.CommentDao;
import com.study.dao.FileDao;
import com.study.dto.ArticleDTO;
import com.study.dto.CommentDTO;
import com.study.dto.FileDTO;
import com.study.utile.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ReadArticleCommand
 * 글 하나를 읽거나, 댓글을 달때 사용하는 command
 */
public class ReadArticleCommand implements Command {

    /**
     * articleDao   : ArticleDao 를 싱글톤 패턴으로 생성해서 호출
     * CommentDao   : commentDao 를 싱글톤 패턴으로 생성해서 호출
     */
    private ArticleDao articleDao = ArticleDao.getInstance();
    private CommentDao commentDao  = CommentDao.getInstance();
    /**
     * execute
     * @param request : HttpServletRequest
     * @param response : HttpServletResponse
     * 요청을 받아서, 만약 seq 가 long 이 아닐경우 잘못된 경로를 참조한것이니, /boards/free/list 로 이동
     * 1. GET
     *    해당 seq 에 해당하는 글 페이지를 보여줌
     * 2. POST
     *    해당 글에 댓글을 작성
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 경로 확인
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        String seq = uri.substring(index + 1);
        if (!isLong(seq)) {
            request.getRequestDispatcher("/boards/free/list").forward(request, response);
            // 에러 발생
            return;
        }
        Long id = Long.parseLong(seq);
        if (request.getMethod().equals("GET")) {

            ArticleDTO article = articleDao.getArticle(id);
            List<CommentDTO> comments = commentDao.getCommentList(id);
            request.setAttribute("article", article);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/view.jsp").forward(request, response);
        }else if (request.getMethod().equals("POST")) {
            CommentDTO comment = new CommentDTO();
            comment.setArticle_id(id);
            comment.setContents(request.getParameter("contents"));
            commentDao.postComment(comment);
        }
    }
}
