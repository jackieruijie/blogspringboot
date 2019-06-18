package com.jackie.blogweb.model;

/**
 * @program: springboot
 * @ClassName: counter
 * @description: counter
 * @author: jackie
 * @create: 2019-06-18 17:17
 * @VERSION:v1.0
 **/

public class Counter {
    private String id;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
