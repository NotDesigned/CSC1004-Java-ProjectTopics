package com.myapp.messages;

import java.io.Serializable;

public class User implements Serializable {
    String UserName;
    String Picture;
    Status status;
    public String getName() {
        return UserName;
    }
    public void setName(String name){
        UserName=name;
    }
    public String getPicture(){
        return Picture;
    }
    public void setPicture(String picture){
        Picture=picture;
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status stat){
        status=stat;
    }
    public User(){}
    public User(String name,String pic,Status stat){UserName=name;Picture=pic;status=stat;}
}

