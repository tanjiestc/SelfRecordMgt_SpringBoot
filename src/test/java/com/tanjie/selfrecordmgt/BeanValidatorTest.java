package com.tanjie.selfrecordmgt;

import com.tanjie.selfrecordmgt.model.Book;
import com.tanjie.selfrecordmgt.model.User;
import com.tanjie.selfrecordmgt.validator.BeanValidator;

import java.util.ArrayList;

public class BeanValidatorTest {
    public static void main(String[] args) {
//        User u = User.builder().username("abc").build();
//        BeanValidator.check(u);
        Book b = Book.builder().name("abc").build();
        Book b2 = Book.builder().name("abcd").build();
        ArrayList<Book> bookArrayList= new ArrayList<>();
        bookArrayList.add(b);
        bookArrayList.add(b2);
        BeanValidator.check(bookArrayList);
    }
}

