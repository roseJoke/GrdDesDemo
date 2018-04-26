package com.grddes.model;

import java.util.List;

public class User {

    public static int MaxManger = 2;
    public static int Manger = 1;
    public static int User_m = 0;
    private Integer uId;

    private String userName;

    private String passWord;

    private Integer uType;


    public User() {
      }

    public User(Integer uId, String userName, String passWord, Integer uType) {
        this.uId = uId;
        this.userName = userName;
        this.passWord = passWord;
        this.uType = uType;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", uType=" + uType +
                '}';
    }
}