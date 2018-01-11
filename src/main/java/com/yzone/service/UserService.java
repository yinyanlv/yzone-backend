package com.yzone.service;

import com.yzone.dto.Result;
import com.yzone.entity.User;

public interface UserService {

    Result<User> addUser(User user) throws RuntimeException;
}
