package om.example.springcloud.servicewebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author 赵正来
 * @date 2019/3/4 11:41
 * @since jdk1.8
 */

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/portfolio","/webSocket")
                .setAllowedOrigins("*")//
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/topic", "/queue");
        registry.enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
                .setRelayHost("http://192.168.141.238")
                .setRelayPort(61613)
                .setClientLogin("admin")
                .setClientPasscode("admin")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);
        //registry.setApplicationDestinationPrefixes("app");
    }
}
