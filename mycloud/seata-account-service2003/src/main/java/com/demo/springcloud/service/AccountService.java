package com.demo.springcloud.service;

import java.math.BigDecimal;

public interface AccountService {
    void decreaseByUserId(Long userId, BigDecimal money);
}
