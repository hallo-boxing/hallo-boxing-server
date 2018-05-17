package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.dal.dao.user.UserDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.user.model.UserDO;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;

/**
 * @author : _Chf
 * @since : 04-05-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "user")
@Api("用户模块")
public class UserController {

    @Autowired
    private UserDOMapperExt userDOMapperExt;

    @PostMapping("/register")
    @ApiOperation("注册")
    public RestResult<Void> register(@RequestBody UserDO userDO) {
        int result = userDOMapperExt.insertSelective(userDO);
        if (result > 0) {
            return RestResult.<Void>builder().success().build();
        }
        return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
    }

    @PostMapping("/login")
    @ApiOperation(("登录"))
    public RestResult<UserDO> login(@RequestParam("mobile") String mobile,
                                    @RequestParam("password") String password) {
        UserDO userDO = userDOMapperExt.selectByMobile(mobile);
        if (userDO == null) {
            return RestResult.<UserDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        if (userDO.getLoginPwd().equals(password)) {
            return RestResult.<UserDO>builder().success(userDO).build();
        }
        return RestResult.<UserDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
    }

}
