package com.wjx.dao;

import java.util.List;

import com.wjx.entity.Moudle;

public interface MoudleMapper {
	void addMoudle(Moudle moudle);
	
	List<Moudle> getMoudleList();
}
