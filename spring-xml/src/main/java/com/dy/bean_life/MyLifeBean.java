package com.dy.bean_life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyLifeBean implements InitializingBean, DisposableBean {

    public MyLifeBean() {
        System.out.println("step1 无参构造器执行");
    }

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        System.out.println("step2 执行setter方法");
    }

    public void init(){
        System.out.println("step5 指定的init方法");
    }

    public void end(){
        System.out.println("step7 指定的的destroy方法");
    }

    public void destroy() throws Exception {
        System.out.println("step6 destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("step4 after propertiesSet");
    }
}
