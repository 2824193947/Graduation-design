package com.imust.service;

import com.imust.entity.Parkinglot;
import com.imust.entity.YuYue;
import com.imust.mapper.ParkinglotMapper;
import com.imust.mapper.YuYueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YuYueService {
	
	@Autowired
	private YuYueMapper yuYuetMapper;
	
	//获取全部
	public List<YuYue> getAll() {
		// TODO Auto-generated method stub
		return yuYuetMapper.findAllYuYue();
	}
	
	public List<YuYue> getAllByKey(int status) {
		// TODO Auto-generated method stub
		return yuYuetMapper.findAllYuYueByKey(status);
	}
	
	public List<YuYue> getByKey(String key) {
		// TODO Auto-generated method stub
		return yuYuetMapper.findYuYueByKey("%"+key+"%");
	}
	//查询
	public YuYue getById(int id) {
		return yuYuetMapper.findYuYueById(id);
	}
	//通过id修改
	public boolean updateYuYue(YuYue yuYue) {
		try {
			yuYuetMapper.updateYuYue(yuYue);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean updateYuYueStatus(YuYue yuYue) {
		try {
			yuYuetMapper.updateYuYueStatus(yuYue);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//删除
	public boolean delYuYue(int id) {
		try {
			yuYuetMapper.deleteYuYue(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addYuYue(YuYue yuYue) {
		try {
			yuYuetMapper.insertYuYue(yuYue);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
