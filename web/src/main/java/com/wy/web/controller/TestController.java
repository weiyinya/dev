package com.wy.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wy
 * @Date 2018/10/23
 */
@Controller
@Slf4j
public class TestController {

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String getInfo(Model model){
        model.addAttribute("name", "its a little dog");
        return "index";
    }

    @RequestMapping(value = "json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> json(){
        log.info("请求串");
        Map<String, Object> result = new HashMap<>();
        result.put("name", "its a little dog");
        result.put("age", null);
        result.put("sex", 1);
        log.info("返回结果{}:" + result);
        return result;
    }

    public static void main(String[] args) throws IOException {

    }

}