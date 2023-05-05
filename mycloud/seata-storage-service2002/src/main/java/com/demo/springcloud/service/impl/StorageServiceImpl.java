package com.demo.springcloud.service.impl;


import com.demo.springcloud.dao.StorageDao;
import com.demo.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decreaseByProductId(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
