package com.github.rolven95.database.model;

import lombok.Data;

@Data
public class BookModel {
    private Integer id;

    private String name;

    private String author;

    private Double price;
}
