package com.yzone.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzone.dto.Result;

@Controller
@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private Result<Map> register(HttpServletRequest request) {

        Map<String, Object> data = new HashMap<String, Object>();

        Result result = new Result<Map>(true, data);

        return result;
    }
}
