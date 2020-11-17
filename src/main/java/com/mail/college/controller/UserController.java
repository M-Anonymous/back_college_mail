package com.mail.college.controller;

import com.mail.college.entity.User;
import com.mail.college.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<User>> list(){
        List<User> list = userMapper.selectList(null);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

}
