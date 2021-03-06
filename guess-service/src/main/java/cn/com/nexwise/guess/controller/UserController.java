package cn.com.nexwise.guess.controller;

import cn.com.nexwise.guess.entity.User;
import cn.com.nexwise.guess.response.ResponseData;
import cn.com.nexwise.guess.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    @ApiOperation(value = "用户信息列表")
    public ResponseData<List<User>> getUsers(){
        List<User> users = userService.getAll();
        return ResponseData.ofSuccess(users, "查询成功");
    }
}
