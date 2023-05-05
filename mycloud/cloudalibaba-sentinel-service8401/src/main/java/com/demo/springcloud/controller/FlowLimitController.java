package com.demo.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController
{

    @GetMapping("/testA")
    public String testA()
    {
        log.info("------testA");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info("------testB");
        return "------testB";
    }

    /**
     *  @SentinelResource主管配置出错，运行出错该走异常走异常
     *  不管运行时异常 eg:int age = 10/0;
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        //int age = 10/0;
        return "------->test hot key";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "------------->deal_testHotKey";
    }


}



























