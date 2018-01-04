package com.yzone.dao;

import com.yzone.BaseTest;
import com.yzone.entity.User;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetUsers() {
        List<User> users = userDao.queryUser();

        assertEquals(0, users.size());
    }
}
