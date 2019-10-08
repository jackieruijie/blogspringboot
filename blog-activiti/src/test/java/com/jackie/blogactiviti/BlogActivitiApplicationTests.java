package com.jackie.blogactiviti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogActivitiApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void createActivitiTable(){
        ProcessEngineConfiguration config=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        config.setJdbcDriver("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.1.75:3306/blogp?useUnicode=true&characterEncoding=utf8");
        config.setJdbcUsername("root");
        config.setJdbcPassword("admin");

        //DB_SCHEMA_UPDATE_TRUE 如果表不存在 自动创建表
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流核心对象 processEngine
        ProcessEngine processEngine = config.buildProcessEngine();
        System.out.println("processEngine:"+processEngine);


    }

}
