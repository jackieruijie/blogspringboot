package com.jackie.bloghandle.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "blog_comment")
public class Comment {

    private String id;

    private Integer userId;

    private Integer articalId;

    private String content;

    private Date time;

    private Boolean isDelete;
}