package cn.com.nexwise.guess.service.impl;

import cn.com.nexwise.guess.entity.User;
import cn.com.nexwise.guess.mapper.UserMapper;
import cn.com.nexwise.guess.service.UserService;
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
