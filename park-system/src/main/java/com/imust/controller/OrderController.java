package com.imust.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.imust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imust.entity.Order;
import com.imust.entity.Park;
import com.imust.entity.Users;
import com.imust.service.OrderService;
import com.imust.service.ParkService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ParkService parkService;
	@Autowired
	private UserService userService;
	//获取列表
	@RequestMapping("/showOrder")
	public String showOrder(Model model,HttpSession session) {
		Users user = (Users)session.getAttribute("LogUser");
		List<Order> orderList = orderService.getByUserId(user.getId());
		model.addAttribute("orderList",orderList);
		System.out.println(orderList);
		return "orderList";
	}
	//删除
	@RequestMapping("/delOrder")
	public String delOrder(@RequestParam("id") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(orderService.delOrder(id)) {
			map.put("res", "0");
		}
		return "redirect:/order/showOrder";
	}
	
	//获取列表
	@RequestMapping("/jiesuan")
	public String jiesuanOrder(HttpSession session,@RequestParam("id") int id,Model model) throws ParseException {
		Order order = orderService.getById(id);
		order.setStatus(1);
		Date updatedate=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("========"+df.format(updatedate));

		Park car = parkService.getById(order.getPark_id());
		Users user = (Users)session.getAttribute("LogUser");
		int p = user.getPoint();
		order.setendTime(df.format(updatedate));
		System.out.println(order.getStartTimeStr());
		System.out.println(df.format(updatedate));
		//计算起始-终止 时间所用的时长
		//计算出两个时间的差值
		long min = CalTime(df.format(updatedate),order.getStartTimeStr());

		double yongshi = (double)(Math.round(min*100/60)/100.0);
		System.out.print(car.getPrice());
		System.out.print(car.getPrice()*yongshi);
		System.out.print(Math.floor(car.getPrice()*yongshi));

		int i = (int) Math.floor(car.getPrice()*yongshi);
		int price = 0;
		if(p>=100&&p<300) {
			order.setTotal((int) Math.floor(car.getPrice()*0.9*yongshi));
			price = (int) Math.floor(car.getPrice()*0.9*yongshi);
		}else if(p>=300&&p<500) {
			order.setTotal((int) Math.floor(car.getPrice()*0.8*yongshi));
			price = (int) Math.floor(car.getPrice()*0.8*yongshi);
		}else if(p>=500) {
			order.setTotal((int) Math.floor(car.getPrice()*0.7*yongshi));
			price = (int) Math.floor(car.getPrice()*0.7*yongshi);
		}else {
			order.setTotal((int) Math.floor(car.getPrice()*yongshi));
			price = (int) Math.floor(car.getPrice()*yongshi);
		}

		if(p > price){
			if(orderService.updateOrderStatus(order)) {
				Park park = parkService.getById(order.getPark_id());
				park.setStatus(0);
				parkService.updateCarStatus(park);
				//98.8 -->98 不进位
				user.setPoint(p-price);
				userService.updatePoint(user);
				session.setAttribute("message","");
			}
		}else{
			session.setAttribute("message","用户的积分不足，无法充值，请您续费！");
		}


		return "redirect:/order/showOrder";
	}
	private static long CalTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long minutes = 0L;
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			minutes = diff / (1000 * 60);
		} catch (ParseException e) {
			System.out.println("抱歉，时间日期解析出错。");
		}
		return minutes;
	}
	public static String txfloat(int a,int b) {
		// TODO 自动生成的方法存根

		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

		return df.format((float)a/b);

	}

}
