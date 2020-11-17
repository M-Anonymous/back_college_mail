package com.mail.college.service.impl;

import com.mail.college.entity.User;
import com.mail.college.mapper.UserMapper;
import com.mail.college.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
