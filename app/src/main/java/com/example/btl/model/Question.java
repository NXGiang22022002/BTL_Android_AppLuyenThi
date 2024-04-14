package com.example.btl.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int question_id;
    private String question_content;
    private Category question_cate;

    public Question() {
    }

    public Question(int question_id, String question_content, Category question_cate) {
        this.question_id = question_id;
        this.question_content = question_content;
        this.question_cate = question_cate;
    }
    // id tự tăng
    public Question(String question_content, Category question_cate) {
        this.question_content = question_content;
        this.question_cate = question_cate;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public Category getQuestion_cate() {
        return question_cate;
    }

    public void setQuestion_cate(Category question_cate) {
        this.question_cate = question_cate;
    }
}
