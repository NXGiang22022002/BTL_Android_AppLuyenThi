package com.example.btl.model;

import java.io.Serializable;

public class PreviousResult implements Serializable {
    private int previousrs_id;
    private int tongdiem;
    private User previouts_rs_user;
    private Category previouts_rs_cate;

    public PreviousResult() {
    }

    public PreviousResult(int previousrs_id, int tongdiem, User previouts_rs_user, Category previouts_rs_cate) {
        this.previousrs_id = previousrs_id;
        this.tongdiem = tongdiem;
        this.previouts_rs_user = previouts_rs_user;
        this.previouts_rs_cate = previouts_rs_cate;
    }

    public PreviousResult(int tongdiem, User previouts_rs_user, Category previouts_rs_cate) {
        this.tongdiem = tongdiem;
        this.previouts_rs_user = previouts_rs_user;
        this.previouts_rs_cate = previouts_rs_cate;
    }

    public int getPreviousrs_id() {
        return previousrs_id;
    }

    public void setPreviousrs_id(int previousrs_id) {
        this.previousrs_id = previousrs_id;
    }

    public int getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(int tongdiem) {
        this.tongdiem = tongdiem;
    }

    public User getPreviouts_rs_user() {
        return previouts_rs_user;
    }

    public void setPreviouts_rs_user(User previouts_rs_user) {
        this.previouts_rs_user = previouts_rs_user;
    }

    public Category getPreviouts_rs_cate() {
        return previouts_rs_cate;
    }

    public void setPreviouts_rs_cate(Category previouts_rs_cate) {
        this.previouts_rs_cate = previouts_rs_cate;
    }
}
