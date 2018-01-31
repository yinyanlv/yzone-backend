package com.yzone.dao;

import com.yzone.entity.User;
import java.util.List;

public interface IUserDao {

    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> queryUser();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);
}
