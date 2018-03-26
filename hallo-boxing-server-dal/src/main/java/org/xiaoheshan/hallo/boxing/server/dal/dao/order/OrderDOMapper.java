package org.xiaoheshan.hallo.boxing.server.dal.dao.order;

import org.xiaoheshan.hallo.boxing.server.dal.dao.order.model.OrderDO;

public interface OrderDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}