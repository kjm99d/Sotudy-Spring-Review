package com.example.study.repository;


public class Member {

    private Long id;

    private String strUserName;
    private String strUserPassword;

    public Member(String strUserName, String strUserPassword) {
        this.strUserName = strUserName;
        this.strUserPassword = strUserPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrUserPassword() {
        return strUserPassword;
    }

    public void setStrUserPassword(String strUserPassword) {
        this.strUserPassword = strUserPassword;
    }
}
