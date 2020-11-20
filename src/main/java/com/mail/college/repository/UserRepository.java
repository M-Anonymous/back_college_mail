package com.mail.college.repository;

import com.mail.college.entity.SimpleUser;
import java.util.List;

/**
 * @author lemon
 */
public interface UserRepository {

    /**
     * 查询所有用户
     * @return
     */
    List<SimpleUser> list();

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SimpleUser queryUserByName(String username);

    /**
     * 新建用户
     * @param user
     * @return
     */
    int insertUser(SimpleUser user);

    /**
     * 根据用户id查询权限集
     * @param id
     * @return
     */
    List<String> queryUserRolesById(int id);


}
