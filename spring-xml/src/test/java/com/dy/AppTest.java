package com.dy;

import com.dy.bean_field.MyBean;
import com.dy.bean_life.MyLifeBean;
import com.dy.bean_register.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    /**
     * 测试bean的注册
     */
    @Test
    public void test01() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        MyService bean = applicationContext.getBean("myService",MyService.class);
        bean.sayHi();

        Object myService01 = applicationContext.getBean("myService01");
        ((MyService)myService01).sayHi();

        Object myService02 = applicationContext.getBean("myService02");
        ((MyService)myService02).sayHi();
    }

    /**
     * 测试bean的属性值注入
     */
    @Test
    public void test02(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        MyBean bean = applicationContext.getBean(MyBean.class);
        System.out.println(bean);
    }

    @Test
    public void test03(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        MyLifeBean lifeBean = applicationContext.getBean(MyLifeBean.class);
        ((ClassPathXmlApplicationContext)applicationContext).close();
    }
}
