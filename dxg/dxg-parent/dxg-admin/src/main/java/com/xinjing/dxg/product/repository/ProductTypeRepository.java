package com.xinjing.dxg.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.product.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, String> {

	@Modifying
	@Query(value = "update t_product_type set is_delete=1 where id =?1 ", nativeQuery = true)
	void remove(String id);
}
