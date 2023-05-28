package com.study.dao;

import com.study.dto.CategoryDTO;
import com.study.utile.Connector;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * CategoryDao 카테고리에 대해서 READ 를 실행하는 객체
 */
public class CategoryDao {
    private SqlSessionFactory factory = Connector.getSqlSession();
    private SqlSession sqlSession;
    private static CategoryDao instance = new CategoryDao();

    /**
     * getInstance
     * @return 생성한 객체 리턴
     * 싱글톤 패턴으로 CategoryDao 생성후 리턴
     */
    public static CategoryDao getInstance() {
        return instance;
    }

    /**
     * getCategoryList
     * @return 카테고리 객체 배열
     * 카테고리를 검색해서 가져옴
     */
    public List<CategoryDTO> getCategoryList(){
        sqlSession = factory.openSession();
        List<CategoryDTO> returnList = (ArrayList)sqlSession.selectList("selectCategoryList");
        sqlSession.close();
        return returnList;
    }
}
