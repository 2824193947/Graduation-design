package com.imust.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.imust.entity.Parkinglot;
import com.imust.service.ParkinglotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imust.entity.Park;
import com.imust.entity.Order;
import com.imust.entity.Users;
import com.imust.service.ParkService;
import com.imust.service.UserService;
import com.imust.service.OrderService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/car")
public class ParkController {
	@Autowired
	private ParkService parkService;
	@Autowired
	private ParkinglotService parkinglotService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/car-select")
	public String getCarByKey(Model model) {
		List<Park> carList = parkService.getAll();
		List<Parkinglot> parkinglotList = parkinglotService.getAll();
		model.addAttribute("parkinglotList",parkinglotList);
		//先循环停车场
		for(Parkinglot p :parkinglotList){
			for(Park pp:carList){
				if(pp.getPkId() == p.getId()){
					pp.setPraklotName(p.getName());
				}
			}
		}
		model.addAttribute("carList",carList);
		model.addAttribute("carNum",carList.size());
		return "list";
	}
	
	@RequestMapping("/findCar")
	public String findCar(@RequestParam("status") int status,Model model) {
		model.addAttribute("status",status);
		if(status==-1) {
			List<Park> carList = parkService.getAll();
			List<Parkinglot> parkinglotList = parkinglotService.getAll();
			model.addAttribute("parkinglotList",parkinglotList);
			//先循环停车场
			for(Parkinglot p :parkinglotList){
				for(Park pp:carList){
					if(pp.getPkId() == p.getId()){
						pp.setPraklotName(p.getName());
					}
				}
			}
			model.addAttribute("carList",carList);
		}else {
			List<Park> carList = parkService.getAllByKey(status);
			List<Parkinglot> parkinglotList = parkinglotService.getAll();
			model.addAttribute("parkinglotList",parkinglotList);
			//先循环停车场
			for(Parkinglot p :parkinglotList){
				for(Park pp:carList){
					if(pp.getPkId() == p.getId()){
						pp.setPraklotName(p.getName());
					}
				}
			}
			model.addAttribute("carList",carList);
			model.addAttribute("carNum",carList.size());
		}
		return "list";
	}
	@RequestMapping("/detail")
	public String editCar(@RequestParam("id") int id,Model model){
		Park car = parkService.getById(id);
		List<Parkinglot> parkinglotList = parkinglotService.getAll();
		model.addAttribute("parkinglotList",parkinglotList);
		//先循环停车场
		for(Parkinglot p :parkinglotList){
				if(car.getPkId() == p.getId()){
					car.setPraklotName(p.getName());
				}
		}
		model.addAttribute("car",car);
		return "detail";
	}
	
	@RequestMapping("/buy")
	public String buy(HttpSession session,@RequestParam("id") int id,@RequestParam("startTime") int startTime,@RequestParam("endTime") int endTime) throws ParseException {

		Date updatedate=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("========"+df.format(updatedate));

		Park car = parkService.getById(id);
		Users user = (Users)session.getAttribute("LogUser");
		int p = user.getPoint();
		car.setStatus(1);
		if(parkService.updateCarStatus(car)) {
			Order order =new Order();
			order.setUser_id(user.getId());
			order.setPark_id(id);
			order.setParkingLot_id(car.getPkId());
			order.setStartTime(df.format(updatedate));
//			if(p>=100&&p<300) {
//				order.setTotal(car.getPrice()*0.9);
//			}else if(p>=300&&p<500) {
//				order.setTotal(car.getPrice()*0.8);
//			}else if(p>=500) {
//				order.setTotal(car.getPrice()*0.7);
//			}else {
//				order.setTotal(car.getPrice());
//			}
			order.setTotal(0);
			if(orderService.addOrder(order)) {
//				user.setPoint(p+10);
//				userService.updatePoint(user);
			}
		}
		return "redirect:/order/showOrder";
	}
	@RequestMapping("/msgContent")
	@ResponseBody
	public String findMsgContent(HttpSession session,int postId,String startTime,String endTime, Model mode) throws ParseException {
		System.out.println(postId);
		String startT = convertDataForT(startTime);
		String endT = convertDataForT(endTime);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

		Park car = parkService.getById(postId);
		Users user = (Users)session.getAttribute("LogUser");
		int p = user.getPoint();
		car.setStatus(1);
		if(parkService.updateCarStatus(car)) {
			Order order =new Order();
			order.setUser_id(user.getId());
			order.setPark_id(postId);
			order.setParkingLot_id(car.getPkId());
			order.setStartTime(startT);
		    order.setendTime(endT);
		if(p>=100&&p<300) {
			order.setTotal(car.getPrice()*0.9);
		}else if(p>=300&&p<500) {
				order.setTotal(car.getPrice()*0.8);
			}else if(p>=500) {
			order.setTotal(car.getPrice()*0.7);
			}else {
			order.setTotal(car.getPrice());
		}
			//order.setTotal(0);
			if(orderService.addOrder(order)) {
				user.setPoint(p+10);
				userService.updatePoint(user);
			}
		}


		return "1";
	}
	public String convertDataForT(String inputData) throws ParseException {
		Date date=new Date();
		SimpleDateFormat datestr=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str="";
		try{
			date=datestr.parse(inputData);
			str = sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(str);
//      String 类型
		return str;

	}
	}
