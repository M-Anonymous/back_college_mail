package com.mail.college.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.mail.college.entity.SimpleUser;
import com.mail.college.mapper.UserMapper;
import com.mail.college.repository.UserRepository;
import com.mail.college.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author lemon
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SimpleUser> implements IUserService, UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SimpleUser user = userRepository.queryUserByName(s);
        if (ObjectUtil.isNotEmpty(user)){
            user.setRoles(userRepository.queryUserRolesById(user.getUid()));
            return new User(user.getUsername(),user.getPassword(),user.getAuthorities());
        }else {
            throw new UsernameNotFoundException("Username or Password is not correct");
        }
    }
}
