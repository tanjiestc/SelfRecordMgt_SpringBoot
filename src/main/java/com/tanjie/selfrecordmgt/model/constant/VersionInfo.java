package com.tanjie.selfrecordmgt.model.constant;

/**
 * @author tanjie
 */

public enum VersionInfo {
    /* 开发环境标识 */
    DEV("dev"),
    /* 正式环境标识 */
    PROD("prod");

    private String name;

    VersionInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
