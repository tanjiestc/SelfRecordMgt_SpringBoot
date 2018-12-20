package com.tanjie.selfrecordmgt.MapperTest;

import com.tanjie.selfrecordmgt.dao.WordsMapper;
import com.tanjie.selfrecordmgt.model.Words;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath*:mapperTest.xml"}) //加载配置文件
public class MapperTest {

    @Resource
    WordsMapper wordsMapper;

    @Test
    public void test() {
        List<Words> wordsList = wordsMapper.selectByExample().build().execute();
        System.out.println(wordsList);
        System.out.println(wordsMapper);

    }
}
