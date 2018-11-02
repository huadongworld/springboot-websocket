package com.ys.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ys.websocket.server.WebSocketServer.getOnlineUsers;
import static com.ys.websocket.server.WebSocketServer.sendMany;
import static com.ys.websocket.server.WebSocketServer.sendMessage;

/**
 * @author HD
 * @date 2018/10/22 14:17
 */
@Controller
@RequestMapping("/dialog")
public class WebsocketController {

    private static final String TEACHER = "老师-";

    private static final String STUDENT = "学生-";

    /**
     * 学生老师建立连接
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/enter/{userName}")
    public ModelAndView student(@PathVariable("userName") String userName) {

        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);

        return new ModelAndView("send/dialog", map);
    }

    /**
     * 老师学生信息推送
     *
     * 一对一单播：学生发语音老师
     *
     * 一对多多播：老师群发语音同学
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/sendMsg/{userName}", method = RequestMethod.POST)
    public ModelAndView sendMsg(String msg, @PathVariable("userName") String userName) {

        //对消息稍微处理一下
        msg = userName + "：" + msg;

        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);

        //获取所有在线的人
        List<String> onlinePeople = getOnlineUsers();

        //获取所有老师
        List<String> teachers = onlinePeople
                .stream()
                .filter(teacher -> teacher.startsWith(TEACHER))
                .collect(Collectors.toList());

        //获取所有同学
        List<String> students = onlinePeople
                .stream()
                .filter(student -> student.startsWith(STUDENT))
                .collect(Collectors.toList());

        if (userName.startsWith(TEACHER)) {
            //群发
            sendMany(msg, students);
        } else if (userName.startsWith(STUDENT)) {
            //单发
            sendMessage(msg, teachers.get(0));
        }

        return new ModelAndView("send/dialog", map);
    }
}
