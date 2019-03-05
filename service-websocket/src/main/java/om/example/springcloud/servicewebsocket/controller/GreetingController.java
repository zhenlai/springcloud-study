package om.example.springcloud.servicewebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    @MessageMapping("/say")
    @SendToUser("/queue/toUser")
    public String handleSay(String greeting){
        System.out.println("say =>" + greeting );
        return "hello web socket!";
    }

    @MessageMapping("/say.{user}")
    @SendToUser("/queue/toUser.{user}")
    public String handleSayVariable(String greeting, @DestinationVariable String user){
        System.out.println(user + "  say =>" + greeting );
        return "hello web socket from user!";
    }
}
