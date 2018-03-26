package org.xiaoheshan.hallo.boxing.server.dal.dao.good;

import org.xiaoheshan.hallo.boxing.server.dal.dao.good.model.GoodDO;

public interface GoodDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodDO record);

    int insertSelective(GoodDO record);

    GoodDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodDO record);

    int updateByPrimaryKey(GoodDO record);
}