package om.example.springcloud.servicewebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author 赵正来
 * @Date 2019/3/4 13:14
 * @since jdk1.8
 */
@Controller
public class GreetingController {

    @MessageMapping("/greeting")
    public String handle(String greeting) {
        System.out.println("=======greeting=======");
        return "5555" + greeting;
    }

    @MessageMapping("/say")
    @SendToUser("/queue/toUser")
    public String handleSay(String greeting){
        System.out.println("say =>" + greeting);
        //messageConverter.
        return "user";
    }
}
