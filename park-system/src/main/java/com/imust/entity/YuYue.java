package com.imust.entity;

import java.util.Date;

public class YuYue {
    private  int id;
    private  int userid;
    private  int status;
    private Date yustartTime;
    private Date yuendTime;
    private int yuid;
    private int pkid;
    private Date shistartTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getYustartTime() {
        return yustartTime;
    }

    public void setYustartTime(Date yustartTime) {
        this.yustartTime = yustartTime;
    }

    public Date getYuendTime() {
        return yuendTime;
    }

    public void setYuendTime(Date yuendTime) {
        this.yuendTime = yuendTime;
    }

    public int getYuid() {
        return yuid;
    }

    public void setYuid(int yuid) {
        this.yuid = yuid;
    }

    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    public Date getShistartTime() {
        return shistartTime;
    }

    public void setShistartTime(Date shistartTime) {
        this.shistartTime = shistartTime;
    }

    public Date getShiendTime() {
        return shiendTime;
    }

    public void setShiendTime(Date shiendTime) {
        this.shiendTime = shiendTime;
    }

    public Double getYprice() {
        return Yprice;
    }

    public void setYprice(Double yprice) {
        Yprice = yprice;
    }

    private Date shiendTime;
    private Double Yprice;



}
