package com.wjx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjx.dao.MoudleMapper;
import com.wjx.entity.Moudle;
import com.wjx.service.MoudleService;

@Service
public class MoudleServiceImpl implements MoudleService {

	@Autowired
	private MoudleMapper moudleMapper;
	
	public void addMoudles(Moudle moudle) {
		moudleMapper.addMoudle(moudle);
		
	}

	public List<Moudle> getMoudleLists() {
		return moudleMapper.getMoudleList();
	}

}
