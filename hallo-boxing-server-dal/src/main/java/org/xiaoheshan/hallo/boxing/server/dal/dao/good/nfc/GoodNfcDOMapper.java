package org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc;

import org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc.model.GoodNfcDO;

public interface GoodNfcDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodNfcDO record);

    int insertSelective(GoodNfcDO record);

    GoodNfcDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodNfcDO record);

    int updateByPrimaryKey(GoodNfcDO record);
}