package com.tanjie.selfrecordmgt.model;

import lombok.Builder;
import lombok.ToString;

import javax.annotation.Generated;

@ToString
@Builder
public class Role {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String roleName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String auth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Role(Integer roleId, String roleName, String auth) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.auth = auth;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Role() {
        super();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getRoleId() {
        return roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRoleName() {
        return roleName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAuth() {
        return auth;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }
}