package org.dy.ioc;

import org.dy.entity.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IoC
 *  ApplicationContext 是 BeanFactory 接口的一个子接口
 *  但是 ApplicationContext 在实现 BeanFactory 中定义的方式时， 是聚合了一个 BeanFactory 接口的实现类，以类似代理的方式去完成的
 *      AbstractRefreshableApplicationContext类即有此体现
 *
 *
 */
public class DependencyInjectionTest {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/application.xml");


        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println("依赖注入 == 创建ioc : " + (userRepository.getBeanFactory() == beanFactory));

        ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext) beanFactory;

        System.out.println("依赖注入 == 创建ioc的内部bean工厂 : " + (userRepository.getBeanFactory() == applicationContext.getBeanFactory()));


    }

}
