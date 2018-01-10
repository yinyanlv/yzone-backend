package com.yzone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "user", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> register(@RequestBody Object params, HttpServletRequest request) {

        Map<String, Object> data = new HashMap<String, Object>();

        return data;
    }
}
