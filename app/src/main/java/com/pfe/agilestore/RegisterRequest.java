package com.pfe.agilestore;

public class RegisterRequest {
    private  Integer tel ;
    private String username,password;

    public String getFullname() {
        return username;
    }

    public void setFullname(String fullname) {
        this.username = fullname;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
