package com.atguigu.sprcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.sprcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author sunziren
 * @date 2021/6/15 12:39
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:"+id+"\t"+"O(∩_∩)O";
    }

    //3秒以内执行正常方法，超时执行备选方案
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        int timeNumber = 3000;
        try {
            TimeUnit.MICROSECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_timeout，id："+id+"\t"+"O(∩_∩)O"+"耗时："+timeNumber+"秒钟";
    }
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" 8001系统繁忙或者运行报错，请稍后再试，id："+id+"\t"+"/(ㄒoㄒ)/~~";
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),    //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("**********id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"；调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数，/(ㄒoㄒ)/~~，id："+id;
    }

}
