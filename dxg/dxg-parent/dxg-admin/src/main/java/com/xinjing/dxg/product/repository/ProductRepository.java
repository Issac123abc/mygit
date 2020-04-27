package com.xinjing.dxg.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	
	@Modifying
	@Query(value = "update t_product set is_delete=1 where id =?1 ", nativeQuery = true)
	void remove(String id);
	
	@Modifying
	@Query(value = "update t_product set status =?2 where id =?1 ", nativeQuery = true)
	void updateStatus(String id, Integer status);
}
