package com.demo.springcloud.controller;

import com.demo.springcloud.domain.CommonResult;
import com.demo.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decreaseByUserId(@RequestParam("userId") Long userId,
                                         @RequestParam("money") BigDecimal money){
        accountService.decreaseByUserId(userId,money);
        return new CommonResult(200,"扣减账户成功！");
    }

    @GetMapping("/test/feign/timeout")
    public CommonResult testTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult(500,"调用超时");
    }


}
