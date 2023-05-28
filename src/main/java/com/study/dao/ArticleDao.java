package com.study.dao;

import com.study.utile.Connector;
import com.study.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * ArticleDao
 * 글에 대해서 CRUD 를 실행하는 객체
 */
public class ArticleDao {
    private SqlSessionFactory factory = Connector.getSqlSession();
    private SqlSession sqlSession;
    private static ArticleDao instance = new ArticleDao();

    /**
     * getInstance.
     * @return 생성한 객체 리턴
     * 싱글톤 패턴으로 ArticleDao 생성후 리턴
     */
    public static ArticleDao getInstance() {
        return instance;
    }

    /**
     * getArticleList
     * @param keyword 검색에 사용할 키워드
     * @return 검색이 완료된 글을 반환
     */
    public List<ArticleDTO> getArticleList(String keyword){
        sqlSession = factory.openSession();
        List<ArticleDTO> returnList;
        if (keyword == null) {
            returnList = (ArrayList)sqlSession.selectList("selectArticleList");
        } else{
            returnList = (ArrayList)sqlSession.selectList("selectArticleListKeyword", keyword);
        }
        sqlSession.close();
        return returnList;
    }

    /**
     * getArticle
     * @param id 가져올 글의 id
     * @return 가져온 글 객체 반환
     * 글을 가져와서 반환하고, 해당 글의 조회수를 1 늘림
     */
    public ArticleDTO getArticle(long id){
        List<ArticleDTO> returnList;
        sqlSession = factory.openSession();
        returnList = (ArrayList)sqlSession.selectList("selectArticleList");
        viewCount(id);
        sqlSession.close();
        return returnList.get(0);
    }

    /**
     * viewCount
     * @param id 조회수를 증가시킬 글의 id
     * 글의 조회수를 1 늘림
     */
    public void viewCount(long id){
        sqlSession.update("viewCount", id);
        sqlSession.commit();
    }

    /**
     * postArticle
     * @param article 작성할 글 객체
     * @return 작성된 글의 id
     * 글을 작성하여 id를 반환
     */
    public long postArticle(ArticleDTO article) {
        sqlSession = factory.openSession();
        long id = sqlSession.update("insertArticle", article);
        sqlSession.commit();
        sqlSession.close();
        return id;
    }

    /**
     * deleteArticle
     * @param id 삭제할 글의 id
     * 글을 삭제
     */
    public void deleteArticle(long id) {
        sqlSession = factory.openSession();
        sqlSession.delete("deleteArticle", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
