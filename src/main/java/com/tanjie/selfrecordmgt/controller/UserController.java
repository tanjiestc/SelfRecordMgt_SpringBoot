package com.tanjie.selfrecordmgt.controller;


import com.tanjie.selfrecordmgt.anno.LoggerManage;
import com.tanjie.selfrecordmgt.exception.SystemException;
import com.tanjie.selfrecordmgt.model.User;
import com.tanjie.selfrecordmgt.model.UserDetailImpl;
import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.model.result.ReturnData;
import com.tanjie.selfrecordmgt.service.UserService;
import com.tanjie.selfrecordmgt.validator.BeanValidator;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tanjie
 * @date 2018年10月26日 21:57:25
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/info")
    @LoggerManage(description = "获取用户信息")
    public Result getUserInfo(Authentication authentication) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        ArrayList<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            String authority1 = authority.getAuthority();
            roles.add(authority1);
        }
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        Result result = Result.success();
        ReturnData data = new ReturnData();
        data.addName(username);
        data.addRoles(roles);
        data.addAvatar(avatar);
        result.setData(data);
        return result;
    }

    /**
     * 1.接收Json数据，转换成User对象。
     *
     * @param user
     * @return
     */
    @PostMapping
    @LoggerManage(description = "用户注册")
    public Result create(@RequestBody User user) {
        BeanValidator.check(user);
        boolean exist = userService.checkUserIsExist(user.getUsername());
        if (exist) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
        User insertUser = userService.insert(user);
        return Result.success(new ReturnData().addObj("user", insertUser));
    }

    @RequestMapping("/logout")
    @LoggerManage(description = "用户登出")
    public Result logout() {
        return Result.success();
    }


    @GetMapping
    @LoggerManage(description = "获取用户列表")
    public Result list() {
        List<User> userList = userService.list();

        Result successResult = Result.success(new ReturnData().addObj("userList", userList));

        return successResult;
    }


    @PutMapping
    @LoggerManage(description = "更新用户信息")
    public Result update(@RequestBody User user) {
        if (user.getEnable() != null && StringUtils.isNotEmpty(user.getUsername())) {
            int i = userService.updateUser(user, user.getUsername());
            if (i == 1) {
                User updateUser = userService.getUserByName(user.getUsername());
                return Result.success(new ReturnData().addUser(updateUser));
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PutMapping("/resetPwd")
    @LoggerManage(description = "重置用户密码")
    public Result resetUserPwd(@RequestBody User user) throws InterruptedException {
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        int i = userService.updateUser(user, user.getUsername());
        Thread.sleep(500);
        if (i == 1) {
            User updateUser = userService.getUserByName(user.getUsername());
            return Result.success(new ReturnData().addUser(updateUser));
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
