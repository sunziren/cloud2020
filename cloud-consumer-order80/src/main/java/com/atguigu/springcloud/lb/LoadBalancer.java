package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author sunziren
 * @date 2021/6/13 18:40
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);

}
