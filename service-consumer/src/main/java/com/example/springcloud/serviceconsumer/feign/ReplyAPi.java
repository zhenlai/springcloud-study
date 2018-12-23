package com.example.springcloud.serviceconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 赵正来
 * @Date 2018/12/6 21:23
 * @since jdk1.8
 */
@FeignClient(value = "service-provider")
public interface ReplyAPi {
    @GetMapping("/reply")
    String reply();
}
