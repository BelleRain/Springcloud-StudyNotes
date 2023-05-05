package com.demo.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "----------> paymentInfoOk 服务调用失败";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "----------> paymentInfoTimeout 服务调用失败";
    }
}
