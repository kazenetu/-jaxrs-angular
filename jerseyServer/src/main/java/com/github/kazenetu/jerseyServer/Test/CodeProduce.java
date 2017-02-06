package com.github.kazenetu.jerseyServer.Test;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

public class CodeProduce {

    @Produces
    @RequestScoped
    @CodeQualifier
    public Code getCode(){
        return new CodeTest();
    }

}
