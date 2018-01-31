package com.yzone.dao;

import com.yzone.BaseTest;
import com.yzone.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IUserDaoTest extends BaseTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testGetUsers() {
        List<User> users = userDao.queryUser();

        assertEquals(0, users.size());
    }
}
