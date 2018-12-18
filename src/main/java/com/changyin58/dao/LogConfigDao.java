package com.changyin58.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface LogConfigDao {
	@Select("SELECT status.status FROM  status where status.parentid=#{channel}")	
	public List<String> getStatusByChannel(@Param("channel")int channel);
	
	@Select("SELECT fingerprint.fingerprint FROM  fingerprint where fingerprint.parentid=#{channel}")	
	public List<String> getFingerprintByChannel(@Param("channel")int channel);
	
	@Select("SELECT content.active FROM  content where content.channel=#{channel}")	
	public Integer getActiveByChannel(@Param("channel")int channel);
}
