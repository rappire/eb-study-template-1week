package com.study.dto;

import java.util.Date;

public class CommentDTO {
    /**
     * id           : 댓글 ID
     * article_id   : 댓글이 어떤 글에 쓰여졌는지 알기 위한 글 ID
     * writer       : 댓글 작성자
     * contents     : 댓글 내용
     *
     */
    private Long id;
    private Long article_id;

    private Date writer;
    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Date getWriter() {
        return writer;
    }

    public void setWriter(Date writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
