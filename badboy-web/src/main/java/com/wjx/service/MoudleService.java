package com.wjx.service;

import java.util.List;

import com.wjx.entity.Moudle;

public interface MoudleService {
	void addMoudles(Moudle moudle);
	
	List<Moudle> getMoudleLists();
}
