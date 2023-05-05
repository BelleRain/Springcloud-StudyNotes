package com.demo.springcloud.controller;


import com.demo.springcloud.service.AccountService;
import com.demo.springcloud.service.OrderService;
import com.demo.springcloud.domain.CommonResult;
import com.demo.springcloud.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/order/create")
    public CommonResult<Order> createOrder(Order order){
        orderService.createOrder(order);
        return new CommonResult(200,"订单创建成功",order);
    }

    @GetMapping("order/test/feign/timeout")
    public CommonResult testTimeout(){
        accountService.testTimeout();
        return new CommonResult(500,"调用超时");
    }

}
