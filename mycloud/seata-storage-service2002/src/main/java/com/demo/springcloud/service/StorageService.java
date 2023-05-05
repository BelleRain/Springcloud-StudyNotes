package com.demo.springcloud.service;

public interface StorageService {

    void decreaseByProductId(Long productId,Integer count);
}
