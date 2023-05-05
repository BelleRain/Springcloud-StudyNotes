package com.demo.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    /**
     * 减少库存
     * @param productId
     * @param count
     */
    void decrease(@Param("productId")Long productId,@Param("count")Integer count);
}
