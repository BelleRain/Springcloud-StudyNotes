package com.demo.springcloud.myHandler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.demo.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception){
        return new CommonResult(444,"自定义的限流处理信息......CustomerBlockHandler",
                exception.getClass().getCanonicalName());
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(444,"自定义的限流处理信息......CustomerBlockHandler",
                exception.getClass().getCanonicalName());
    }

}
