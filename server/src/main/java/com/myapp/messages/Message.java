package com.myapp.messages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

/*
This part is the overall information that client would probably communicate with the server.
*/
public class Message implements Serializable{
    private String name;
    private MsgType type;
    private String msg;
    private int count;
    private Vector<User> users,list;
    private Status status;
    private byte[] voiceMsg;

    public byte[] getVoiceMsg() {
        return voiceMsg;
    }

    private String picture;

    public Message() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {return msg;}

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {this.type = type; }
    public String getPicture(){return picture;}

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Vector<User> getUsers() {
        return users;
    }

    public void setUsers(Vector<User> users) {
        this.users = users;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setVoiceMsg(byte[] voiceMsg) {
        this.voiceMsg = voiceMsg;
    }
    public void setUserlist(HashMap<String, User> userList) {
        list = new Vector<>(userList.values());
    }
}
