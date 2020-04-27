package com.xinjing.dxg.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xinjing.dxg.manager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	@Query(value = "select * from t_user where phone =?1 and password =?2 and type =?3 and is_delete=0 ", nativeQuery = true)
	User findByPhoneAndPasswordAndType(String phone, String password, String type);
	
	User findByIdAndIsDelete(String userId, Integer isDelete);
}
