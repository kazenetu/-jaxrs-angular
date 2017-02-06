package com.github.kazenetu.jerseyServer.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CodeTest extends Code {
    @PostConstruct
    private void postConstruct(){
        System.out.println("CodeTest postConstruct!");
    }

    @PreDestroy
    private void destory(){
        System.out.println("CodeTest destory!");
    }
}

