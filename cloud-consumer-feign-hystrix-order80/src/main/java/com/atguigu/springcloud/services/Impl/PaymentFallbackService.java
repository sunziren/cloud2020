package com.atguigu.springcloud.services.Impl;

import com.atguigu.springcloud.services.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author sunziren
 * @date 2021/6/16 18:59
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentFallbackService-paymentInfo_ok-/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "PaymentFallbackService-paymentInfo_timeout-/(ㄒoㄒ)/~~";
    }
}
