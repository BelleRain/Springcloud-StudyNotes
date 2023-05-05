package com.demo.springcloud.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    /**
     * 扣减账户
     * @param userId
     * @param money
     */
    void decrease(@Param("userId")Long userId, @Param("money")BigDecimal money);



}
