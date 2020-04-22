package com.xinjing.dxg.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.manager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
