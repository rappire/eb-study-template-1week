package com.study.dto;

public class CategoryDTO {
    private long id;
    private String category;
    /**
     * id           : 카테고리 ID
     * category     : 카테고리 이름
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
