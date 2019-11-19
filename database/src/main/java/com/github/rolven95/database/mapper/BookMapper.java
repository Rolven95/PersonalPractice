package com.github.rolven95.database.mapper;

import com.github.rolven95.database.model.BookModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    int insert(BookModel record);

    List<BookModel> selectAll();

    BookModel getById(@Param(value = "id") Integer id);
}