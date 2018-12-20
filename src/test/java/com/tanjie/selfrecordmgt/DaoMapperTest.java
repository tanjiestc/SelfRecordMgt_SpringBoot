package com.tanjie.selfrecordmgt;

import com.tanjie.selfrecordmgt.dao.BookMapper;
import com.tanjie.selfrecordmgt.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.tanjie.selfrecordmgt.dao.BookDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoMapperTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    BookMapper bookMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void mapperTest() {
    }

    @Test
    public void bookTest() throws Exception {
//        String result = mockMvc.perform(get("/book")
////                .param("id","1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);


        String content = "{\"id\":\"14\", \"name\":\"updateHa\",\"author\":null,\"quantity\":" + 56 + "}";
        String content2 = "{\"name\":\"newInsert\",\"author\":\"Helelo\",\"quantity\":" + 56 + "}";
//        String reuslt = mockMvc.perform(put("/book/14").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(content))
//                .andExpect(status().isOk())
////                .andExpect(jsonPath("$..id").value("14"))
//                .andReturn().getResponse().getContentAsString();


        String reuslt = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("14"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(reuslt);





    }






}
