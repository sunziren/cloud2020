package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.services.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunziren
 * @date 2021/6/16 15:20
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallBackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_ok(id);
        log.info("*******result: "+result);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    //3秒以内执行正常方法，超时执行备选方案
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
//    })
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_timeout(id);
        log.info("*******result: "+result);
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "我是消费者80，对方支付系统繁忙或者运行报错，请稍后再试，id："+id+"\t"+"/(ㄒoㄒ)/~~";
    }

    //下面是全局fallback
    public String payment_Global_FallBackMethod(){
        return "Global异常信息处理，请稍后再试————";
    }

}
