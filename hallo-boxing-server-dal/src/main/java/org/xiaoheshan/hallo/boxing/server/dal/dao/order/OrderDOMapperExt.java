package org.xiaoheshan.hallo.boxing.server.dal.dao.order;

import org.apache.ibatis.annotations.Mapper;
import org.xiaoheshan.hallo.boxing.server.dal.dao.order.model.OrderDO;

import java.util.List;

/**
 * orderMapper
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@Mapper
public interface OrderDOMapperExt extends OrderDOMapper {

    List<OrderDO> selectOrderByUserId(Integer userId);
}