package cn.com.nexwise.guess.mapper;

import cn.com.nexwise.guess.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
