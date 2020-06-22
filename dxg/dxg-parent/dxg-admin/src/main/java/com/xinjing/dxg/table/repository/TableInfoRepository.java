package com.xinjing.dxg.table.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.table.entity.TableInfo;

@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo, String> {
	
	@Query(value = "select * from t_table where status=0 and user_id =?1 limit ?2 offset ?3 ", nativeQuery = true)
	List<TableInfo> findByUserId(String userId, Integer limit, Integer offset);
	
	@Query(value = "select count(1) from t_table where status=0 and user_id =?1 ", nativeQuery = true)
	Long totalByUserId(String userId);
}
