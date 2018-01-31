package com.yzone.service.impl;

import com.yzone.dao.IUserDao;
import com.yzone.dto.Result;
import com.yzone.entity.User;
import com.yzone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public Result<User> addUser(User user) throws RuntimeException {

        if (user != null && user.getUsername() != null && user.getPassword() != null) {

            Date date = new Date();

            user.setRole(0);
            user.setState(1);
            user.setCreateTime(date);
            user.setUpdateTime(date);

            try {
                int effectedNum = 0;

                effectedNum = userDao.insertUser(user);

                if (effectedNum <= 0) {

                    return new Result(false, "注册用户失败");
                }

                return new Result(true, user);
            } catch (Exception e) {

                throw new RuntimeException("增加用户失败");
            }
        } else {

            throw new RuntimeException("用户名或密码不能为空");
        }
    }
}
