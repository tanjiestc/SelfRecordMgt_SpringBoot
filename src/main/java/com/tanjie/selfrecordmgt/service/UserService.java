package com.tanjie.selfrecordmgt.service;

import com.tanjie.selfrecordmgt.dao.UserMapper;
import com.tanjie.selfrecordmgt.exception.SystemException;
import com.tanjie.selfrecordmgt.model.User;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.tanjie.selfrecordmgt.dao.RoleDynamicSqlSupport.role;
import static com.tanjie.selfrecordmgt.dao.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author tanjie
 */
@Service
public class UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        user.setEnable(true);
        user.setRoleId(1);
        user.setLastPasswordChange(System.currentTimeMillis());
        userMapper.insert(user);
        return user;
    }

    public boolean checkUserIsExist(String inputUserName) {
        return 1 == userMapper.countByExample().where(username, isEqualTo(inputUserName)).build().execute();
    }

    public List<User> list() {
        SelectStatementProvider statementProvider =
                select(username, lastPasswordChange, enable, user.roleId, role.auth)
                        .from(user)
                        .leftJoin(role)
                        .on(user.roleId, equalTo(role.roleId))
                        .where(user.roleId, isNotEqualTo(2))
                        .build()
                        .render(RenderingStrategy.MYBATIS3);
        return userMapper.selectJoinRoleMany(statementProvider);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User u) {
        UpdateStatementProvider statement = update(user)
                .set(user.enable)
                .equalTo(u.getEnable())
                .where(username, isEqualTo(u.getUsername()))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        return userMapper.update(statement);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User u, String userName) {
        return userMapper.updateByExampleSelective(u).where(username, isEqualTo(userName)).build().execute();
    }


    public User getUserByName(String name) {
        return userMapper.selectByPrimaryKey(name);
    }


    public boolean userLoginEnable(boolean enable) throws SystemException {
        return enable;
    }


}
