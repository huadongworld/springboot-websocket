package com.ys.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/22 14:17
 */
@Controller
@RequestMapping("/socket")
public class WebsocketController {

    @RequestMapping(value = "/send/{userName}")
    public ModelAndView send(@PathVariable("userName") String userName) {

        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        return new ModelAndView("send/dialog", map);
    }
}
