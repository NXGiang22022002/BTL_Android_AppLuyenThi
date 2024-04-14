package com.example.btl.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String img;
    private String name,username, password;
    private int is_admin;

    public User() {
    }

    public User(int id, String img, String name, String username, String password, int is_admin) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    public User(String img, String name, String username, String password, int is_admin) {
        this.img = img;
        this.name = name;
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }
}
