package com.tanjie.selfrecordmgt.service.impl;

import com.tanjie.selfrecordmgt.dao.UserMapper;
import com.tanjie.selfrecordmgt.model.LoginDetail;
import com.tanjie.selfrecordmgt.model.TokenDetail;
import com.tanjie.selfrecordmgt.service.LoginService;
import com.tanjie.selfrecordmgt.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, TokenUtils tokenUtils) {
        this.userMapper = userMapper;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public LoginDetail getLoginDetail(String username) {
        return userMapper.getUserFromDatabase(username);
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }


}
