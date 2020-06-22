package com.xinjing.dxg.table.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinjing.dxg.common.PageData;
import com.xinjing.dxg.common.PagerParam;
import com.xinjing.dxg.common.utils.UUIDUtil;
import com.xinjing.dxg.table.entity.TableInfo;
import com.xinjing.dxg.table.repository.TableInfoRepository;

@Service
public class TableService {

	@Autowired
	private TableInfoRepository tableInfoRepository;
	
	public void add(String name, Integer number){
		TableInfo table = new TableInfo();
		table.setId(UUIDUtil.uuid());
		table.setName(name);
		table.setPeople(number);
		table.setStatus(0);
		Date now = new Date();
		table.setCreateTime(now);
		table.setUpdateTime(now);
		tableInfoRepository.save(table);
	}
	
	public PageData<TableInfo> getPageList(String userId, PagerParam pageParam){
		Integer pageSize = pageParam.getPageSize();
		int offset = pageSize * (pageParam.getPageNumber() -1);
		List<TableInfo> list = tableInfoRepository.findByUserId(userId, pageSize, offset);
		Long total = tableInfoRepository.totalByUserId(userId);
		return new PageData<>(pageSize, pageParam.getPageNumber(), total, list);
	}
	
}
