package com.example.mainactivity.Domain;

public class CategoryDomain {
    private String title;
    private int id;
    private String picUrl;


    public CategoryDomain(String title, int id, String imgUrl) {
        this.title = title;
        this.id = id;
        this.picUrl = imgUrl;
    }


    public CategoryDomain() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String imgUrl) {
        this.picUrl = imgUrl;
    }
}
