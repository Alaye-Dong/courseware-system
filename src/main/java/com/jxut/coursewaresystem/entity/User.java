package com.jxut.coursewaresystem.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String sex;
    private String address;
    private String tel;
    private String type;
    private String birthday;
    private String if_valid;

    public User() {
    }

    public User(String username, String realname, String password, String birthday, String sex, String address, String tel, String type) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.tel = tel;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIf_valid() {
        return if_valid;
    }

    public void setIf_valid(String if_valid) {
        this.if_valid = if_valid;
    }
}
