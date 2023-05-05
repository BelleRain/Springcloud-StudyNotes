package com.demo.springcloud.controller;


import com.demo.springcloud.domain.CommonResult;
import com.demo.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult decreaseByProductId(@RequestParam("productId")Long productId,
                                            @RequestParam("count")Integer count){
        storageService.decreaseByProductId(productId,count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
