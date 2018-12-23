package com.example.springcloud.serviceconsumer;

import com.example.springcloud.serviceconsumer.feign.ReplyAPi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Autowired private ReplyAPi replyAPi;

    @RequestMapping("/say")
    public String toSay(){

        return replyAPi.reply();
    }
}
