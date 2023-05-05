package com.demo.springcloud.controller;


import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import com.demo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        boolean save = paymentService.save(payment);
        log.info("插入操作的返回结果------> {}",save);
        if (save){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,save);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getById(id);
        if (payment != null){
            return new CommonResult(200,"查询数据成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据失败，查询ID"+ id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public DiscoveryClient discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t"+ instance.getHost() + "\t" + instance.getPort() +"\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        System.out.println("**********paymentTimeout serverPort: " + serverPort);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/lb")
    public String serverLb(){
        return serverPort;
    }


}














































