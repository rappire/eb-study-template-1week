package com.study.utile;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Connector
 * SqlSessionFactory 를 생성하는 객체
 */
public class Connector {
    private static SqlSessionFactory sqlSession;
    static{

        String resource = "./mybatis/mybatis-config.xml";
        try{
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSession = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("실패");        }
    }

    /**
     * getSqlSession
     * @return 생성한 SqlSessionFactory 리턴
     * 싱글톤 패턴으로 SqlSessionFactory 생성후 리턴
     */
    public static SqlSessionFactory getSqlSession() {
        return sqlSession;
    }
}
