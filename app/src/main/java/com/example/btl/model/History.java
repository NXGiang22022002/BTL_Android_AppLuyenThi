package com.example.btl.model;

import java.io.Serializable;

public class History implements Serializable {
    private int history_id;
    private int history_diem;
    private User user;
    private Category category;

    public History() {
    }

    public History(int history_id, int diem, User user, Category category) {
        this.history_id = history_id;
        this.history_diem = diem;
        this.user = user;
        this.category = category;
    }

    public History(int history_diem, User user, Category category) {
        this.history_diem = history_diem;
        this.user = user;
        this.category = category;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getHistory_diem() {
        return history_diem;
    }

    public void setHistory_diem(int history_diem) {
        this.history_diem = history_diem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
