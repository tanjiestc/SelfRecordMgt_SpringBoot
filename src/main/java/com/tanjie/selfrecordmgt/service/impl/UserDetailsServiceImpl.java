package com.tanjie.selfrecordmgt.service.impl;

import com.tanjie.selfrecordmgt.dao.UserMapper;
import com.tanjie.selfrecordmgt.model.SecurityModelFactory;
import com.tanjie.selfrecordmgt.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring Security 用于将 数据库中的用户信息转换成 userDetail 对象的服务类的实现类
 *
 * @author tanjie
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    /**
     * 获取 userDetail
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMapper.getUserFromDatabase(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return SecurityModelFactory.create(user);
        }
    }

}
