package org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good;

import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.model.CabinetGoodDO;

public interface CabinetGoodDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CabinetGoodDO record);

    int insertSelective(CabinetGoodDO record);

    CabinetGoodDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CabinetGoodDO record);

    int updateByPrimaryKey(CabinetGoodDO record);
}