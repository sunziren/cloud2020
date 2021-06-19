package com.aitguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author sunziren
 * @date 2021/6/18 23:22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class AppConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigCenterMain3344.class,args);
    }

}
