package com.study.dto;

import java.io.InputStream;

public class FileDTO {
    /**
     * id           : 파일 ID
     * article_id   : 파일이 어떤 글에 저장되었는지 알기 위한 글 ID
     * file_name    : 파일 저장소에 있는 파일 이름
     * original_name: 파일 원래 이름
     *
     */
    private long id;
    private long article_id;
    private String file_name;
    private String original_name;

    public long getArticleId() {
        return article_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArticleId(long articleId) {
        this.article_id = articleId;
    }

    public String getFileName() {
        return file_name;
    }

    public void setFileName(String fileName) {
        this.file_name = fileName;
    }

    public String getFile() {
        return original_name;
    }

    public void setFile(String file) {
        this.original_name = file;
    }
}
