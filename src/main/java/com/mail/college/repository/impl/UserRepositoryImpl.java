package com.mail.college.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mail.college.entity.SimpleUser;
import com.mail.college.mapper.UserMapper;
import com.mail.college.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<SimpleUser> list() {
        return userMapper.selectList(null);
    }

    @Override
    public SimpleUser queryUserByName(String username) {
        return userMapper.selectOne(new QueryWrapper<SimpleUser>().eq(SimpleUser.USERNAME,username));
    }

    @Override
    public int insertUser(SimpleUser user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public List<String> queryUserRolesById(int id) {
        return userMapper.queryUserRolesById(id);
    }

}
