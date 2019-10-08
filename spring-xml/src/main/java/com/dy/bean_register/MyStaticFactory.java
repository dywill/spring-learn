package com.dy.bean_register;

public class MyStaticFactory {

    public static MyService myService(){
        return new MyService();
    }
}
