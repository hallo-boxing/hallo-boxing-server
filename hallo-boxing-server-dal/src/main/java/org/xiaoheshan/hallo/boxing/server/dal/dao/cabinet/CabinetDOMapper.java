package org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet;

import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.model.CabinetDO;

public interface CabinetDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CabinetDO record);

    int insertSelective(CabinetDO record);

    CabinetDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CabinetDO record);

    int updateByPrimaryKey(CabinetDO record);
}