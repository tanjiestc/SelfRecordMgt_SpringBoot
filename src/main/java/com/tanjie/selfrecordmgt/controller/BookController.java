package com.tanjie.selfrecordmgt.controller;

import com.tanjie.selfrecordmgt.model.Book;
import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.model.result.ReturnData;
import com.tanjie.selfrecordmgt.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tanjie
 */
@RestController
@RequestMapping("/book")
@Api(tags = "书籍管理")
public class BookController {

    @Resource
    BookService bookService;

    @GetMapping(value = "/{id:\\d+}")
    public Result getBookById(@PathVariable(value = "id") int id) {
        Book book = bookService.getBookById(id);
        return Result.success(new ReturnData().addObj("book", book));
    }

    @GetMapping
    @ApiOperation("获取所有书籍列表")
    public Result list() {
        List<Book> list = bookService.list();
        return Result.success(new ReturnData().addObj("bookList", list));
    }

    @PostMapping("/search")
    public Result search(@RequestBody Book book) {
        List<Book> bookList = bookService.findByLikely(book);
        return Result.success(new ReturnData().addObj("bookList", bookList));
    }


    @PutMapping(value = "/{id:\\d+}")
    public Result update(@RequestBody Book book, @PathVariable(value = "id") int id) {
        Integer updateId = id;
        book.setId(updateId);
        int update = bookService.update(book);
        if (update == 1) {
            Book afterUpdateBook = bookService.getBookById(updateId);
            return Result.success(new ReturnData().addObj("book", afterUpdateBook));
        }
        return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
    }


    /**
     * 增
     *
     * @param book book
     * @return {@link Result}
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Book book) {
        bookService.insert(book);
        return Result.success(new ReturnData().addObj("id", book.getId()));
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public Result update(@PathVariable(value = "id") int id) {
        bookService.delete(id);
        return Result.success(new ReturnData().addObj("id", id));
    }

}
