package com.demo.springcloud.service;

import com.demo.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
@FeignClient(value = "seata-account-service")
public interface AccountService {

    /**
     * 扣减账户余额
     */
    //@RequestMapping(value = "/account/decrease",
    // method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @PostMapping("/account/decrease")
    CommonResult decreaseByUserId(@RequestParam("userId") Long userId,
                                  @RequestParam("money")BigDecimal money);

    /**
     * feign超时测试
     * @return
     */
    @GetMapping("/test/feign/timeout")
    CommonResult testTimeout();
}
