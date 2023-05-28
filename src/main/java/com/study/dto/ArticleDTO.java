package com.study.dto;

import java.util.Date;

public class ArticleDTO {

    /**
     * id           : 글 ID
     * category_id  : 카테고리 테이블에서 카테고리를 불러오기 위한 ID
     * title        : 글 제목
     * writer       : 글 작성자
     * views        : 조회수
     * reg_date     : 글 등록날짜
     * edit_date    : 글 수정날짜
     * password     : 비밀번호
     * contents     : 글 내용
     *
     */
    private Long id;
    private Long category_id;
    private String category;
    private String title;
    private String writer;
    private int views;
    private Date reg_date;
    private Date edit_date;
    private String password;
    private String contents;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getRegDate() {
        return reg_date;
    }

    public void setRegDate(Date regDate) {
        this.reg_date = regDate;
    }

    public Date getEditDate() {
        return edit_date;
    }

    public void setEditDate(Date editDate) {
        this.edit_date = editDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
