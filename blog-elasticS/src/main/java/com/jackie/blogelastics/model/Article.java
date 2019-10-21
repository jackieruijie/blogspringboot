package com.jackie.blogelastics.model;

import lombok.Data;

import java.util.Date;

@Data
public class Article  {
    private String id;
    private String author;
    private String content;
    private Date publishTime;

}
