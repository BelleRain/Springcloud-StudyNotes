package com.demo.springcloud.service.impl;

import com.demo.springcloud.dao.AccountDao;
import com.demo.springcloud.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;


    @Override
    public void decreaseByUserId(Long userId, BigDecimal money) {

        //模拟订单超时
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId,money);
    }
}
