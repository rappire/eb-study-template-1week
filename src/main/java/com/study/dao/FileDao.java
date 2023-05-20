package com.study.dao;

import com.study.connection.Connector;
import com.study.dto.FileDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDao {

    private static FileDao instance = new FileDao();

    public static FileDao getInstance() {
        return instance;
    }

    public FileDTO getFileByArticle(long articleId) {
        FileDTO vo = null;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(
                " SELECT id, articleId, fileName, file");
            query.append(" FROM file ");
            query.append(" WHERE  articleId = ? ");
            PreparedStatement state = conn.prepareStatement(query.toString());
            state.setLong(1, articleId);
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                vo = new FileDTO();
                vo.setId(rs.getLong("id"));
                vo.setArticleId(rs.getLong("articleId"));
                vo.setFileName(rs.getString("fileName"));
                vo.setFile(rs.getBinaryStream("file"));
            }
            return vo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public FileDTO getFileById(long Id) {
        FileDTO vo = null;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(
                " SELECT id, articleId, fileName, file");
            query.append(" FROM file ");
            query.append(" WHERE  id = ? ");
            PreparedStatement state = conn.prepareStatement(query.toString());
            state.setLong(1, Id);
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                vo = new FileDTO();
                vo.setId(rs.getLong("id"));
                vo.setArticleId(rs.getLong("articleId"));
                vo.setFileName(rs.getString("fileName"));
                vo.setFile(rs.getBinaryStream("file"));
            }
            return vo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void postFile(FileDTO file) {
        String resultState;
        try {
            Connector con = Connector.getInstance();
            Connection conn = con.getConnection();
            StringBuffer query = new StringBuffer();
            query.append(" INSERT INTO file");
            query.append(" (articleId, fileName, file)");
            query.append(" VALUE(?, ?, ?)");
            PreparedStatement state = conn.prepareStatement(query.toString());
            state.setLong(1, file.getArticleId());
            state.setString(2, file.getFileName());
            state.setBlob(3, file.getFile());
            System.out.println(state);
            int result = state.executeUpdate();
            if (result == 1) {
                resultState = "success";
            } else {
                resultState = "fail";
            }
            System.out.println(resultState);
        } catch (
            SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
