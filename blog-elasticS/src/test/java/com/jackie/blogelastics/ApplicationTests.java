package com.jackie.blogelastics;

import com.jackie.blogelastics.model.Book;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {
        Book book=new Book();
        book.setAuthor("admin");
        book.setBookName("这是书名");
        book.setBookNo(UUID.randomUUID().toString().replaceAll("-",""));
        book.setPrice(30.00);
        book.setId(UUID.randomUUID().toString().replaceAll("-",""));
        //构建一个索引功能
        Index build = new Index.Builder(book).index("jackie").type("book").build();
        try {
            DocumentResult execute = jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //测试搜索功能
    @Test
    public void  esSearch(){
        String searchCondition="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"bookName\" : \"书名\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(searchCondition).addIndex("jackie").addType("book").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
