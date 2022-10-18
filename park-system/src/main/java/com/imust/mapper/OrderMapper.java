package com.imust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imust.entity.Order;
@Mapper
public interface OrderMapper {
	

	@Select("SELECT \n" +
			"  o.status,\n" +
			"  u.plate_num,\n" +
			"  c.price,\n" +
			"  o.code,\n" +
			"  o.createDate,\n" +
			"  o.id,\n" +
			"  o.total,\n" +
			"  u.name user_name,\n" +
			"  u.phone,\n" +
			"  c.name park_name,\n" +
			"  p.name parkingLot_name \n" +
			"FROM\n" +
			"  Orders o,\n" +
			"  USER u,\n" +
			"  Park c,\n" +
			"  parkinglot p \n" +
			"WHERE o.user_id = u.id \n" +
			"  AND o.park_id = c.id \n" +
			"  AND o.parkingLot_id = p.id ")
	List<Order> findAllOrder();
	
	//查询信息
	@Select("select o.status,u.plate_num,c.price,o.code,o.createDate,o.id,o.total,u.name user_name,u.phone,c.name park_name from Orders o,User u,Park c where o.user_id=u.id and o.park_id=c.id and (u.plate_num like #{key} or u.name like #{key})")
	List<Order> findByKey(@Param("key") String key);
	
	//查询信息
	@Select("SELECT \n" +
			"  o.status,\n" +
			"  u.plate_num,\n" +
			"  c.price,\n" +
			"  o.code,\n" +
			"  o.createDate,\n" +
			"  o.id,\n" +
			"  o.total,\n" +
			"  u.name user_name,\n" +
			"  u.phone,\n" +
			"  c.name park_name ,\n" +
			"  P.name parkingLot_name \n" +
			"FROM\n" +
			"  Orders o,\n" +
			"  USER u,\n" +
			"  Park c,\n" +
			"  parkinglot p\n" +
			"WHERE o.user_id = u.id \n" +
			"  AND o.park_id = c.id \n" +
			"  AND O.parkingLot_id = P.id\n" +
			"  AND user_id = #{id} order by createDate desc ")
	List<Order> findByUserId(@Param("id") int id);
	
	@Select("select * from Orders where id = #{id}")
	Order findById(@Param("id") int id);
	
	//添加信息
	@Insert("insert into Orders(code,user_id,park_id,createDate,status,total,parkingLot_id,startTime,endTime) values(UUID(),#{user_id},#{park_id},SYSDATE(),0,#{total},#{parkingLot_id},#{startTime},#{endTime})")
    public void insertOrder(Order order);
	
	//删除信息
	@Delete("delete from Orders where id=#{id}")
	public void deleteOrder(int id);
	
	
	//修改信息
	@Update("update Orders set status=#{status},endTime=#{endTime},total=#{total} where id=#{id}")
	public void updateStatus(Order order);
}
