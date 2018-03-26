package org.xiaoheshan.hallo.boxing.server.dal.dao.good.appraisal;

import org.xiaoheshan.hallo.boxing.server.dal.dao.good.appraisal.model.GoodAppraisalDO;

public interface GoodAppraisalDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodAppraisalDO record);

    int insertSelective(GoodAppraisalDO record);

    GoodAppraisalDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodAppraisalDO record);

    int updateByPrimaryKey(GoodAppraisalDO record);
}