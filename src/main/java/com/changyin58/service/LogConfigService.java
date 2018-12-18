package com.changyin58.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.changyin58.dao.LogConfigDao;


@Service
public class LogConfigService {
	

	@Resource
	private LogConfigDao logConfigDao;
	
	public List<String> getStatusByChannel(int channel) {
		
		return logConfigDao.getStatusByChannel(channel);
	}

	public List<String> getFingerprintByChannel(int channel) {
		// TODO Auto-generated method stub
		return logConfigDao.getFingerprintByChannel(channel);
	}
	
	public Integer getActiveByChannel(int channel) {
		return logConfigDao.getActiveByChannel(channel);
	}
}
