package com.yzone.controller;

import javax.servlet.http.HttpServletRequest;

import com.yzone.dto.Register;
import com.yzone.entity.User;
import com.yzone.service.IUserService;
import com.yzone.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzone.dto.Result;

@Controller
@RequestMapping(value = "/api/register", method = {RequestMethod.POST})
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private Result<User> register(HttpServletRequest request, @RequestBody Register params) {

        String username = params.getUsername();
        String password = params.getPassword();
        String repeatPassword = params.getRepeatPassword();
        String email = params.getEmail();

        if (username.isEmpty()) {
            return new Result<User>(false, "用户名不能为空");
        }

        if (password.isEmpty()) {
            return new Result<User>(false, "密码不能为空");
        }

        if (repeatPassword.isEmpty()) {
            return new Result<User>(false, "重复密码不能为空");
        }

        if (email.isEmpty()) {
            return new Result<User>(false, "邮箱不能为空");
        }

        if (!password.equals(repeatPassword)) {
            return new Result<User>(false, "两次密码输入不一致");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5.getMd5(password));
        user.setEmail(email);

        return userService.addUser(user);
    }
}
