package cn.com.nexwise.all.mapper;

import cn.com.nexwise.all.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
