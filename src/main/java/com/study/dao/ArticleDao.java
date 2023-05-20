package com.study.dao;

import com.study.connection.Connector;
import com.study.dto.ArticleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    private static ArticleDao instance = new ArticleDao();

    public static ArticleDao getInstance() {
        return instance;
    }

    public List<ArticleDTO> getArticleList() {
        List<ArticleDTO> returnList = new ArrayList<ArticleDTO>();
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(
                " SELECT id, kategorie, title, writer, views, regDate, editDate, fileCheck");
            query.append(" FROM article ");
            PreparedStatement state = conn.prepareStatement(query.toString());
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                ArticleDTO vo = new ArticleDTO();
                vo.setId(rs.getLong("id"));
                vo.setKategorie(rs.getString("kategorie"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setViews(rs.getInt("views"));
                vo.setRegDate(rs.getDate("regDate"));
                vo.setEditDate(rs.getDate("editDate"));
                vo.setFileCheck(rs.getBoolean("fileCheck"));
                returnList.add(vo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return returnList;
    }

    public ArticleDTO getArticle(Long id) {
        ArticleDTO vo = null;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(
                " SELECT kategorie, title, writer, views, regDate, editDate, contents, fileCheck, password");
            query.append(" FROM article ");
            query.append(" WHERE  id = ? ");
            PreparedStatement state = conn.prepareStatement(query.toString());
            state.setLong(1, id);
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                vo = new ArticleDTO();
                vo.setId(id);
                vo.setKategorie(rs.getString("kategorie"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setViews(rs.getInt("views"));
                vo.setRegDate(rs.getDate("regDate"));
                vo.setEditDate(rs.getDate("editDate"));
                vo.setContents(rs.getString("contents"));
                vo.setFileCheck(rs.getBoolean("fileCheck"));
                vo.setPassword(rs.getString("password"));
            }
            return vo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long postArticle(ArticleDTO article) {
        String resultState;
        long id = -1;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(" INSERT INTO article");
            query.append(" (kategorie, title, writer, views, password, contents, fileCheck)");
            query.append(" VALUE(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement state = conn.prepareStatement(query.toString());
            state.setString(1, article.getKategorie());
            state.setString(2, article.getTitle());
            state.setString(3, article.getWriter());
            state.setInt(4, 0);
            state.setString(5, article.getPassword());
            state.setString(6, article.getContents());
            state.setBoolean(7, article.getFileCheck());
            System.out.println(state);
            int result = state.executeUpdate();
            if (result == 1) {
                state = conn.prepareStatement(" SELECT LAST_INSERT_ID()");
                ResultSet resultSet = state.executeQuery();
                resultSet.next();
                id = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public long putArticle(ArticleDTO article) {
        long id = -1;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(" UPDATE article SET");
            query.append(" title = ?,");
            query.append(" writer = ?,");
            query.append(" contents = ?");
            query.append(" WHERE  id = ? ");
            PreparedStatement state = conn.prepareStatement(query.toString());
            System.out.println(query.toString());
            state.setString(1, article.getTitle());
            state.setString(2, article.getWriter());
            state.setString(3, article.getContents());
            state.setLong(4, article.getId());
            System.out.println(state);
            int result = state.executeUpdate();
            if (result == 1) {
                state = conn.prepareStatement(" SELECT LAST_INSERT_ID()");
                ResultSet resultSet = state.executeQuery();
                resultSet.next();
                id = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
