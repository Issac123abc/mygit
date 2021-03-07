package cn.com.nexwise.common.service.impl;

import cn.com.nexwise.common.entity.User;
import cn.com.nexwise.common.mapper.UserMapper;
import cn.com.nexwise.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        List<User> all = userMapper.findAll();
        return all;
    }
}
