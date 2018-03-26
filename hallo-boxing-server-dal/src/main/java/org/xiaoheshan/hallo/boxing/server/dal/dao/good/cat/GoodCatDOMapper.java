package org.xiaoheshan.hallo.boxing.server.dal.dao.good.cat;

import org.xiaoheshan.hallo.boxing.server.dal.dao.good.cat.model.GoodCatDO;

public interface GoodCatDOMapper {
    int deleteByPrimaryKey(Integer catId);

    int insert(GoodCatDO record);

    int insertSelective(GoodCatDO record);

    GoodCatDO selectByPrimaryKey(Integer catId);

    int updateByPrimaryKeySelective(GoodCatDO record);

    int updateByPrimaryKey(GoodCatDO record);
}