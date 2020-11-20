package com.mail.college.mapper;

import com.mail.college.entity.SimpleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lemon
 */
@Mapper
public interface UserMapper extends BaseMapper<SimpleUser>{

    /**
     * 根据用户id查询权限集
     * @param id
     * @return
     */
    List<String> queryUserRolesById(int id);

}
