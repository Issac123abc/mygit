package cn.com.nexwise.common.mapper;

import cn.com.nexwise.common.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
