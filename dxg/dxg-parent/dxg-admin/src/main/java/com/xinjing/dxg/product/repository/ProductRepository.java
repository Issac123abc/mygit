package com.xinjing.dxg.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
