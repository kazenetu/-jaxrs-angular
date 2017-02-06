package com.github.kazenetu.jerseyServer.Test;

import org.glassfish.hk2.api.Factory;

public class TestFactory implements Factory<TestInterface> {

    @Override
    public TestInterface provide() {
        System.out.println("TestInterface provide!");

        return new TestClass();
        //return null;
    }

    @Override
    public void dispose(TestInterface instance) {
        System.out.println("TestInterface dispose!");
        instance.close();

    }

}
