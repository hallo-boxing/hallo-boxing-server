package org.xiaoheshan.hallo.boxing.server.service.enums;

import org.springframework.util.Assert;

import static org.xiaoheshan.hallo.boxing.server.service.constant.PiCommandConstant.COMMAND_PREFIX;

/**
 * @author : _Chf
 * @since : 03-28-2018
 */
public enum PiCommandEnum {

    CHECKOUT(COMMAND_PREFIX + "CHECKOUT"),
    DOOR(COMMAND_PREFIX + "DOOR"),
    CAMERA(COMMAND_PREFIX + "CAMERA"),
    ;

    private String name;

    PiCommandEnum(String name) {
        this.name = name;
    }

    public String get() {
        return this.name;
    }

    public String getWithParam(String... params) {
        Assert.notNull(params);
        Assert.isTrue(params.length >= 1);
        StringBuilder builder = new StringBuilder(name);
        builder.append('=');
        for (String param : params) {
            builder.append(param).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
