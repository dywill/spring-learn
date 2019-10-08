package spring_aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_aop.util.DateUtil;

import java.util.Date;

public class MyTest {

    ApplicationContext applicationContext;

    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    @Test
    public void test01() {
        DateUtil dateUtil = applicationContext.getBean(DateUtil.class);
        String transfer = dateUtil.transfer(new Date());
    }
}
