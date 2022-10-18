package com.imust.entity;
import java.util.Date;

public class Parkinglot {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public int getParkTotal() {
        return parkTotal;
    }

    public void setParkTotal(int parkTotal) {
        this.parkTotal = parkTotal;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private int id;
    private String name;
    private String locate;
    private int parkTotal;
    private int isOpen;
    private Date createDate;
    private double price;


}
