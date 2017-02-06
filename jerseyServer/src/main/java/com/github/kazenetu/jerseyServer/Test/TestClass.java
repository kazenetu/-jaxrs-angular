package com.github.kazenetu.jerseyServer.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestClass implements TestInterface{
    public String msg(){
        System.out.println("TestClass new!");
        return "TestClass";
    }

    public void close(){
        System.out.println("TestClass close!");
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println("TestClass postConstruct!");
    }

    @PreDestroy
    private void destory(){
        System.out.println("TestClass destory!");
    }
}
