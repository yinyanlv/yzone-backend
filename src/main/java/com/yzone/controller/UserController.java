package com.yzone.controller;

import javax.servlet.http.HttpServletRequest;

import com.yzone.entity.User;
import com.yzone.service.UserService;
import com.yzone.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzone.dto.Result;

@Controller
@RequestMapping(value = "/register", method = {RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private Result<User> register(HttpServletRequest request) {

        String username = new String(request.getParameter("username"));
        String password = new String(request.getParameter("password"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5.getMd5(password));

        return userService.addUser(user);
    }
}
