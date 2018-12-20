package com.tanjie.selfrecordmgt;

import com.tanjie.selfrecordmgt.dao.RoleMapper;
import com.tanjie.selfrecordmgt.dao.UserDynamicSqlSupport;
import com.tanjie.selfrecordmgt.dao.UserMapper;
import com.tanjie.selfrecordmgt.model.User;
import com.tanjie.selfrecordmgt.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.tanjie.selfrecordmgt.dao.UserDynamicSqlSupport.*;
import static com.tanjie.selfrecordmgt.dao.RoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelfrecordmgtApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;



    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void userTest() {
        Long count = userMapper
                .countByExample()
                .where(username, isEqualTo("admin"))
                .build()
                .execute();
        System.out.println(count);

    }

    @Test
    public void userServiceTest() {
//        boolean exist = userService.checkUserIsExist("admin11");
//        System.out.println(exist);

        SelectStatementProvider statementProvider =
                select(username, lastPasswordChange, enable, user.roleId, role.auth)
                        .from(user)
                        .leftJoin(role)
                        .on(user.roleId, equalTo(role.roleId))
                        .where(user.roleId, isNotEqualTo(2))
                        .build()
                        .render(RenderingStrategy.MYBATIS3);
        List<User> userList = userMapper.selectJoinRoleMany(statementProvider);

        for (User u : userList) {
            System.out.println(u);
        }

    }

    @Test
    public void updateSqlTest() {
        User u = new User();
        u.setUsername("newUser01");
        u.setPassword("123456");
        u.setRoleId(2);
        u.setEnable(false);
        u.setLastPasswordChange(null);
//        userMapper.updateByExample(u).where(username,isEqualTo(u.getUsername())).build().execute();
        userMapper.updateByExampleSelective(u).where(username, isEqualTo("newUser01")).build().execute();

    }


    @Test
    public void whenGetInfoSuccess() throws Exception {
        String content = "{\"username\":\"admin\",\"password\":123456}";
        String result = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void getInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/info")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }
}
