package com.example.todo.models;

import java.util.Date;

public class Tasks {
    private int id;
    private String nameTask;
    private Date date;
    private  Byte isCom;
    private int catid;
    private int userid;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Byte getIsCom() {
        return isCom;
    }

    public void setIsCom(Byte isCom) {
        this.isCom = isCom;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Tasks() {
    }

    public Tasks(int id, String nameTask, Date date, Byte isCom, int catid, int userid) {
        this.id = id;
        this.nameTask = nameTask;
        this.date = date;
        this.isCom = isCom;
        this.catid = catid;
        this.userid = userid;
    }

    public Tasks(String nameTask, Date date, Byte isCom) {
        this.nameTask = nameTask;
        this.date = date;
        this.isCom = isCom;
    }
}
