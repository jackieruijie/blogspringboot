package com.jackie.bloghandle.model;

import lombok.Data;

@Data
public class ArticleWithBLOBs extends Article {

    private String content;

    private String summary;

}