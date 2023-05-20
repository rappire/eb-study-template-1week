package com.study.dto;

import java.io.InputStream;

public class FileDTO {

    private long id;
    private long articleId;
    private String fileName;
    private InputStream file;

    public long getArticleId() {
        return articleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
}
