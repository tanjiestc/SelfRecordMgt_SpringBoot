package com.tanjie.selfrecordmgt.service;

import com.tanjie.selfrecordmgt.dao.BookMapper;
import com.tanjie.selfrecordmgt.model.Book;
import com.tanjie.selfrecordmgt.utils.BeanUtils;
import com.tanjie.selfrecordmgt.validator.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.tanjie.selfrecordmgt.dao.BookDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author tanjie
 */
@Service
public class BookService {

    @Resource
    BookMapper bookMapper;

    public List<Book> list() {
        return bookMapper.selectByExample().build().execute();
    }

    public List<Book> findByLikely(Book bookVo) {
        List<Book> bookList =
                bookMapper.selectByExample()
                        .where(name, isLikeWhenPresent("%" + bookVo.getName() + "%"))
                        .and(author, isLikeWhenPresent("%" + bookVo.getAuthor() + "%"))
                        .build().execute();
        return bookList;
    }

    public void insert(Book book) {
        BeanValidator.check(book);
        bookMapper.insert(book);
    }

    public int update(Book book) {
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public Book getBookById(int id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    public void delete(int id) {
        bookMapper.deleteByPrimaryKey(id);
    }


}
