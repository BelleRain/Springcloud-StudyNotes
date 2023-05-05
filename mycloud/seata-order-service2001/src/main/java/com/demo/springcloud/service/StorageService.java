package com.demo.springcloud.service;

import com.demo.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    @PostMapping("/storage/decrease")
    CommonResult decreaseByProductId(@RequestParam("productId")Long productId,
                                     @RequestParam("count")Integer count);
}
