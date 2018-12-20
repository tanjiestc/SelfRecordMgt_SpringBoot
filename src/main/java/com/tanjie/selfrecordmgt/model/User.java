package com.tanjie.selfrecordmgt.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Generated;
import javax.validation.constraints.NotBlank;

@ToString
public class User implements LoginDetail, TokenDetail{

    // 视图1
    public interface BasicView {}
    // 视图2 继承视图1
    public interface TwoView extends BasicView {}

    @NotBlank(message = "用户名不能为空！")
    @Length(message = "用户名的长度在6~12之间", min = 6 ,max = 12)
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String username;

    @NotBlank(message = "密码不能为空！")
    @Length(message = "密码的长度在6~12之间", min = 6 ,max = 15)
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long lastPasswordChange;

    private String authorities;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean enable;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public User(String username, String password, Integer roleId, Long lastPasswordChange, Boolean enable) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.lastPasswordChange = lastPasswordChange;
        this.enable = enable;
    }



    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public User() {}

    @JsonView(BasicView.class)
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @JsonView(BasicView.class)
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUsername() {
        return username;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPassword() {
        return password;
    }

    @Override
    public boolean enable() {
        return this.enable;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getRoleId() {
        return roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @JsonView(BasicView.class)
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getLastPasswordChange() {
        return lastPasswordChange;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLastPasswordChange(Long lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    @JsonView(BasicView.class)
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getEnable() {
        return enable;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}