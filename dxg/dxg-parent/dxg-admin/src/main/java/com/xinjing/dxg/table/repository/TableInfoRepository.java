package com.xinjing.dxg.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.table.entity.TableInfo;

@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo, String> {

}
