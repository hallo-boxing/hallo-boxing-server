package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.common.util.FileUtils;
import org.xiaoheshan.hallo.boxing.server.common.util.HttpUtils;
import org.xiaoheshan.hallo.boxing.server.dal.dao.image.ImageDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.image.model.ImageDO;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author : _Chf
 * @since : 05-08-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "image")
@Api("图片控制器")
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageDOMapperExt imageDOMapperExt;

    @PostMapping("/upload")
    @ApiOperation("上传照片")
    public RestResult<Integer> upload(@RequestParam("file") MultipartFile file) {
        logger.info("上传图片：" + file.getName());
        String filename = file.getOriginalFilename();
        File imageFile = FileUtils.makeImageFile(filename);
        try {
            FileCopyUtils.copy(file.getBytes(), imageFile);
        } catch (IOException e) {
            return RestResult.<Integer>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        ImageDO imageDO = new ImageDO();
        imageDO.setImgPath(imageFile.getAbsolutePath());
        imageDO.setCreateTime(new Date());
        imageDO.setImgSize((int) file.getSize());
        imageDO.setDataFlag((byte) 0);
        int result = imageDOMapperExt.insertSelective(imageDO);
        if (result < 0) {
            return RestResult.<Integer>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Integer>builder().success(imageDO.getImgId()).build();
    }

    @GetMapping("/download/{img_id}")
    @ApiOperation("下载照片")
    public ResponseEntity<FileSystemResource> download(@PathVariable("img_id") Integer imgId) {
        logger.info("下载照片：" + imgId);
        ImageDO imageDO = imageDOMapperExt.selectByPrimaryKey(imgId);
        if (imageDO == null) {
            return null;
        }
        return HttpUtils.export(new File(imageDO.getImgPath()));
    }

}
