package com.ys.websocket.controller;

import com.ys.websocket.server.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/22 14:17
 */
@Controller
@RequestMapping("/dialog")
public class WebsocketController {

    @RequestMapping(value = "/enter/{userName}")
    public ModelAndView enter(@PathVariable("userName") String userName) {

        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);

        return new ModelAndView("send/student", map);
    }

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public ModelAndView sendMsg(String msg) {

        WebSocketServer.sendAll(msg);

        return new ModelAndView("send/student");
    }
}
