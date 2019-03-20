package om.example.springcloud.servicewebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 赵正来
 * @date 2019/3/4 13:14
 * @since jdk1.8
 */
@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @PostMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("====post====");
        messagingTemplate.convertAndSend("/topic/toUser","java");
        return "hello send";
    }

    @MessageMapping("/greeting")
    public String handle(String greeting) {
        System.out.println("=======greeting=======");
        return "5555" + greeting;
    }

    @MessageMapping("/exchange/topic.user")
    @SendTo("/exchange/topic.user/user1")
    public String handleSay(String greeting){
        System.out.println("say =>" + greeting );
        return "hello web socket! /topic.user/user1";
    }

    @MessageMapping("/say.{user}")
    @SendToUser("/topic/toUser.{user}")
    public String handleSayVariable(String greeting, @DestinationVariable String user){
        System.out.println(user + "  say =>" + greeting );
        return "hello web socket from user!";
    }

    @PostMapping("/sayToUser")
    @ResponseBody
    public String sayToUser(String message,String user){
        System.out.println("====sayToUser====");
        messagingTemplate.convertAndSendToUser(user,"/user/topic/toUser.zzl",   message);
        return "hello send";
    }
}
