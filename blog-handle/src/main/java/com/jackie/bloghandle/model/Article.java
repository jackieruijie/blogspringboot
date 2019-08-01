package com.jackie.bloghandle.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "bolg_article")
public class Article {

    private String id;

    private String title;

    private Integer userId;

    private Integer sysCategoryId;

    private Integer categoryId;

    private Date publishTime;

    private Boolean isTop;

    private Boolean isDelete;

    private Integer count;
}