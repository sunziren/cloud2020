package com.atguigu.sprcloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sunziren
 * @date 2021/6/15 12:37
 */

public interface PaymentService {

    String paymentInfo_ok(Integer id);

    String paymentInfo_timeout(Integer id);

    String paymentCircuitBreaker(Integer id);

}
