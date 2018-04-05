package org.xiaoheshan.hallo.boxing.server.service.enums;

/**
 * @author : _Chf
 * @since : 03-28-2018
 */
public enum ResponseEnum {
    OK("OK"),
    ERROR("ERROR"),
    ;

    private String name;

    ResponseEnum(String name) {
        this.name = name;
    }

    public String get() {
        return this.name;
    }

    public boolean is(String other) {
        return other != null && !other.isEmpty() && other.toUpperCase().startsWith(name);
    }

}
