package org.xiaoheshan.hallo.boxing.server.dal.dao.image;

import org.xiaoheshan.hallo.boxing.server.dal.dao.image.model.ImageDO;

public interface ImageDOMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ImageDO record);

    int insertSelective(ImageDO record);

    ImageDO selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(ImageDO record);

    int updateByPrimaryKey(ImageDO record);
}