package com.jackie.blogelastics.model;

import io.searchbox.annotations.JestId;
import lombok.Data;

@Data
public class Book {

    @JestId //表明这个es主键id
    private String id;
    private String bookName;
    private String author;
    private Double price;
    private String bookNo;
}
