package com.imust.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int id;
	private String code;
	private String user_name;
	private String phone;
	private String plate_num;
	private String park_name;
	private String parkingLot_name;
	private double price;
	private double total;
	private int user_id;
	private int park_id;
	private int parkingLot_id;
	private int status;
	private Date createDate;
	private Date startTime;
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setStartTime(String date) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			this.startTime = f.parse(date);

	}
	public String getStartTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.startTime);
	}
	public void setendTime(String date) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.endTime = f.parse(date);

	}
	public String getendTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.endTime);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getParkingLot_id() {
		return parkingLot_id;
	}

	public void setParkingLot_id(int parkingLot_id) {
		this.parkingLot_id = parkingLot_id;
	}

	public String getParkingLot_name() {
		return parkingLot_name;
	}

	public void setParkingLot_name(String parkingLot_name) {
		this.parkingLot_name = parkingLot_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlate_num() {
		return plate_num;
	}
	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}
	public String getPark_name() {
		return park_name;
	}
	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPark_id() {
		return park_id;
	}
	public void setPark_id(int park_id) {
		this.park_id = park_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                ", plate_num='" + plate_num + '\'' +
                ", park_name='" + park_name + '\'' +
                ", parkingLot_name='" + parkingLot_name + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", user_id=" + user_id +
                ", park_id=" + park_id +
                ", parkingLot_id=" + parkingLot_id +
                ", status=" + status +
                ", createDate=" + createDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
