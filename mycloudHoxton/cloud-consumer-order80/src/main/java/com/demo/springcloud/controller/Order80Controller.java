package com.demo.springcloud.controller;


import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import com.demo.springcloud.lb.ILoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class Order80Controller {

    //使用https会出现证书信任问题
    private static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ILoadBalancer iLoadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    //客户端发送的是Get请求，但底层实质是发送post请求调用8001服务器
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(
                PaymentSrv_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(
                PaymentSrv_URL+"/payment/get/"+id,CommonResult.class,id);
    }

    @GetMapping("/consumer/getEntity/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> result = restTemplate.getForEntity(
                PaymentSrv_URL + "/payment/get/" + id, CommonResult.class, id);
        if(result.getStatusCode().is2xxSuccessful()){
            log.info("状态1-->{}",result.getStatusCode());
            return result.getBody();
        }else {
            return new CommonResult(444,"操作失败",null);
        }
    }

    @GetMapping("consumer/postEntity/create")
    public CommonResult create2(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(
                PaymentSrv_URL + "/payment/create", payment, CommonResult.class);
        log.info("状态2-->{}",entity.getStatusCode());
        return entity.getBody();
    }

    @GetMapping("/consumer/payment/lb")
    public String serverLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = iLoadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        log.info("uri----->{}",uri);
        String object = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return object;
    }

}





























