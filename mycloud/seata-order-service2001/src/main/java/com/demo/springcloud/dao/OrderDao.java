package com.demo.springcloud.dao;

import com.demo.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 添加订单
     * @param order
     */
    void create(Order order);

    /**
     * 根据用户id更改订单状态，0付款失败，1付款成功
     * @param userId
     * @param status
     */
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
