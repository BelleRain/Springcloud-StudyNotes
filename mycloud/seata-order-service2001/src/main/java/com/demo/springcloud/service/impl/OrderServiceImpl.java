package com.demo.springcloud.service.impl;


import com.demo.springcloud.dao.OrderDao;
import com.demo.springcloud.domain.Order;
import com.demo.springcloud.service.AccountService;
import com.demo.springcloud.service.OrderService;
import com.demo.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    /**
     * 创建订单 ——》调用库存服务扣减库存 ——》调用账户服务扣减账户 ——》改变订单状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "create-order",rollbackFor = Exception.class)
    public void createOrder(Order order) {
        log.info("创建订单");
        orderDao.create(order);

        log.info("减库存");
        storageService.decreaseByProductId(order.getProductId(),order.getCount());
        log.info("远程库存服务调用完成");


        log.info("减账户");
        accountService.decreaseByUserId(order.getUserId(),order.getMoney());
        log.info("远程账户服务调用完成");

        log.info("改状态");
        orderDao.update(order.getUserId(),order.getStatus());
        log.info("订单状态修改完成");
    }

}


















































