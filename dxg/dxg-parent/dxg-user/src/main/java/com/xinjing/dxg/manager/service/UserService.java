package com.xinjing.dxg.manager.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinjing.dxg.common.utils.MD5Util;
import com.xinjing.dxg.common.utils.RedisUtil;
import com.xinjing.dxg.common.utils.UUIDUtil;
import com.xinjing.dxg.manager.entity.User;
import com.xinjing.dxg.manager.model.UserModel;
import com.xinjing.dxg.manager.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void add(String phone, String password, String email) {
		Date now = new Date();
		User user = new User();
		user.setId(UUIDUtil.uuid());
		user.setPhone(phone);
		user.setPassword(MD5Util.GetMD5Code(password));
		user.setEmail(email);
		user.setType(2);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setIsDelete(0);
		userRepository.save(user);
	}
	
	public String login(String phone, String password, String type) throws Exception {
		password = MD5Util.GetMD5Code(password);
		User user = userRepository.findByPhoneAndPasswordAndType(phone, password, type);
		if (user == null) {
			throw new RuntimeException("账号或密码错误");
		}
		String token = UUIDUtil.uuid();
		RedisUtil.set(token, user.getId(), 60000 * 30);
		return token;
	}
	
	public void logout(String token){
		RedisUtil.remove(token);
	}
	
	public User getDetail(String userId){
		User user = userRepository.findByIdAndIsDelete(userId, 0);
		return user;
	}
	
	public void edit(UserModel model) throws RuntimeException {
		Optional<User> findById = userRepository.findById(model.getId());
		if(!findById.isPresent()){
			throw new RuntimeException("用户不存在");
		}
		User user = findById.get();
		BeanUtils.copyProperties(model, user);
		user.setUpdateTime(new Date());
		userRepository.save(user);
	}
}
