package com.github.rolven95.database.controller;

import com.alibaba.fastjson.JSON;
import com.github.rolven95.database.model.BookModel;
import com.github.rolven95.database.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class TestController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBook/{id}")
    String bookInfo(@PathVariable("id") Integer id) {
        BookModel bookModel = bookService.getById(id);
        log.info("Get book model:{}", bookModel);
        return JSON.toJSONString(bookModel);
    }
}