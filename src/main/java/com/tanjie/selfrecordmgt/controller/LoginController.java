package com.tanjie.selfrecordmgt.controller;


import com.tanjie.selfrecordmgt.anno.LoggerManage;
import com.tanjie.selfrecordmgt.model.LoginDetail;
import com.tanjie.selfrecordmgt.model.TokenDetail;
import com.tanjie.selfrecordmgt.model.param.RequestLoginUser;
import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.model.result.ReturnData;
import com.tanjie.selfrecordmgt.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author tanjie
 */
@RestController
@Api(tags = "登录", description = "登录接口")
public class LoginController {


    private final LoginService loginService;

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "登录")
    @ApiOperation(value = "登录", httpMethod = "POST", notes = "登录验证通过返回token")
    public Result login(@Valid @RequestBody RequestLoginUser requestLoginUser, BindingResult bindingResult) {
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR);
        }
        LoginDetail loginDetail = loginService.getLoginDetail(requestLoginUser.getUsername());
        Result ifLoginFail = checkAccount(requestLoginUser, loginDetail);
        if (ifLoginFail != null) {
            return ifLoginFail;
        }
        String token = loginService.generateToken((TokenDetail) loginDetail);

        ReturnData data = new ReturnData();
        data.addObj(tokenHeader, token);

        return Result.success(data);
    }

    private Result checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail) {
        if (loginDetail == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        } else {
            if (!loginDetail.enable()) {
                return Result.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
            }
            boolean matches = bCryptPasswordEncoder.matches(requestLoginUser.getPassword(), loginDetail.getPassword());
            if (!matches) {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        }
        return null;
    }

}
