package com.jackie.bloghandle.mapper;

import com.jackie.bloghandle.model.Article;
import com.jackie.bloghandle.model.ArticleWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}