package com.study.dao;

import com.study.dto.ArticleDTO;
import com.study.dto.CommentDTO;
import com.study.utile.Connector;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * CommentDao 댓글에 대해서 READ, CREATE 를 실행하는 객체
 */
public class CommentDao {
    private SqlSessionFactory factory = Connector.getSqlSession();
    private SqlSession sqlSession;
    private static CommentDao instance = new CommentDao();

    /**
     * getInstance
     * @return 생성한 객체 리턴
     * 싱글톤 패턴으로 CommentDao 생성후 리턴
     */
    public static CommentDao getInstance() {
        return instance;
    }

    /**
     * getCommentList
     * @param id 댓글을 가져올 글의 id
     * @return 가져온 댓글 객체 배열
     * 글에 해당하는 댓글을 가져와서 리턴
     */
    public List<CommentDTO> getCommentList(long id){
        sqlSession = factory.openSession();
        List<CommentDTO> returnList = (ArrayList)sqlSession.selectList("selectCommentList", id);
        sqlSession.close();
        return returnList;
    }

    /**
     * postComment.
     * @param comment 작성할 댓글 객체
     * 댓글을 작성
     */
    public void postComment(CommentDTO comment){
        sqlSession = factory.openSession();
        sqlSession.update("insertComment", comment);
        sqlSession.commit();
        sqlSession.close();
    }
}
