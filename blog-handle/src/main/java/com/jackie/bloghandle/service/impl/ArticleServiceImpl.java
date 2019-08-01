package com.jackie.bloghandle.service.impl;

import com.jackie.bloghandle.mapper.ArticleMapper;
import com.jackie.bloghandle.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticleIntoMongdb() {
//        Template
        return 0;
    }
}
