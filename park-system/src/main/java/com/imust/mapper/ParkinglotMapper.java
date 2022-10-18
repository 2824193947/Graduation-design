package com.imust.mapper;

import com.imust.entity.Park;
import com.imust.entity.Parkinglot;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParkinglotMapper {
	

	@Select("select * from parkinglot")
	List<Parkinglot> findAllParkinglot();
	
	@Select("select * from parkinglot where  isOpen = #{isOpen}")
	List<Parkinglot> findAllParkinglotByKey(@Param("isOpen") int isOpen);

	@Select("select * from parkinglot where  name like #{key}")
	List<Parkinglot> findParkinglotByKey(@Param("key") String key);
	
	@Select("select * from parkinglot where id=#{id}")
	Parkinglot findParkinglotById(@Param("id") int id);
	
	//添加信息
	@Insert("insert into parkinglot(name,locate,parkTotal,isOpen,createDate,price) values(#{name},#{locate},#{parkTotal},#{isOpen},#{createDate},#{price})")
    public void insertParkinglot(Parkinglot parkinglot);
	
	//删除信息
	@Delete("delete from parkinglot where id=#{id}")
	public void deleteParkinglot(int id);
	
	@Update("update parkinglot set isOpen=#{isOpen} where id=#{id}")
	public void updateParkinglotStatus(Parkinglot parkinglot);
	
	//修改信息
	@Update("update parkinglot set name=#{name},locate=#{locate},parkTotal=#{parkTotal},isOpen=#{isOpen},price=#{price} where id=#{id}")
	public void updateParkinglot(Parkinglot parkinglot);
}
