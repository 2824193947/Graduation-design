package com.imust.service;

import com.imust.entity.Park;
import com.imust.entity.Parkinglot;
import com.imust.mapper.ParkinglotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkinglotService {
	
	@Resource
	private ParkinglotMapper parkinglotMapper;
	
	//获取全部
	public List<Parkinglot> getAll() {
		// TODO Auto-generated method stub
		return parkinglotMapper.findAllParkinglot();
	}
	
	public List<Parkinglot> getAllByKey(int status) {
		// TODO Auto-generated method stub
		return parkinglotMapper.findAllParkinglotByKey(status);
	}
	
	public List<Parkinglot> getByKey(String key) {
		// TODO Auto-generated method stub
		return parkinglotMapper.findParkinglotByKey("%"+key+"%");
	}
	//查询
	public Parkinglot getById(int id) {
		return parkinglotMapper.findParkinglotById(id);
	}
	//通过id修改
	public boolean updateParkinglot(Parkinglot parkinglot) {
		try {
			parkinglotMapper.updateParkinglot(parkinglot);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean updateParkinglotStatus(Parkinglot parkinglot) {
		try {
			parkinglotMapper.updateParkinglotStatus(parkinglot);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//删除
	public boolean delParkinglot(int id) {
		try {
			parkinglotMapper.deleteParkinglot(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addParkinglot(Parkinglot parkinglot) {
		try {
			parkinglotMapper.insertParkinglot(parkinglot);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
