package com.study.dao;

import com.study.utile.Connector;
import com.study.dto.FileDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FileDao
 * 파일에 대해서 CRUD 를 실행하는 객체
 */
public class FileDao {

    private static FileDao instance = new FileDao();

    public static FileDao getInstance() {
        return instance;
    }

}
