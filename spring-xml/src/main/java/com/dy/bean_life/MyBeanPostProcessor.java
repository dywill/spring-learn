package com.dy.bean_life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myLifeBean")){
            System.out.println("step3 bean后置处理器 before");
        }

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myLifeBean")){
            System.out.println("step6 bean后置处理器 after");
        }
        return bean;
    }
}
