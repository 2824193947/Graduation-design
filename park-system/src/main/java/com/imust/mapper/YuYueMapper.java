package com.imust.mapper;

import com.imust.entity.Park;
import com.imust.entity.YuYue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface YuYueMapper {
	

	@Select("select * from yuyue")
	List<YuYue> findAllYuYue();
	
	@Select("select * from yuyue where  status = #{status}")
	List<YuYue> findAllYuYueByKey(@Param("status") int status);
	
	@Select("select * from yuyue where  pkid like #{pkid}")
	List<YuYue> findYuYueByKey(@Param("pkid") String pkid);
	
	@Select("select * from yuyue where id=#{id}")
	YuYue findYuYueById(@Param("id") int id);
	
	//添加信息
	@Insert("insert into yuyue(userid,status,yustartTime,yuendTime,yuid,pkid,shistartTime,shiendTime,Yprice) values(#{userid},#{status},#{yustartTime},#{yuendTime},#{yuid},#{pkid},#{shistartTime},#{shiendTime},#{Yprice})")
    public void insertYuYue(YuYue yuyue);
	
	//删除信息
	@Delete("delete from yuyue where id=#{id}")
	public void deleteYuYue(int id);
	
	@Update("update yuyue set isOpen=#{isOpen} where id=#{id}")
	public void updateYuYueStatus(YuYue yuyue);
	
	//修改信息
	@Update("update yuyue set userid=#{userid},status=#{status},yustartTime=#{yustartTime},yuendTime=#{yuendTime},yuid=#{yuid},pkid=#{pkid},shistartTime=#{shistartTime},shiendTime=#{shiendTime},Yprice=#{Yprice} where id=#{id}")
	public void updateYuYue(YuYue yuyue);
}
