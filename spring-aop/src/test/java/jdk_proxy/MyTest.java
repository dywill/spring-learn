package jdk_proxy;

import jdk_proxy.factory.MathProxyFactory;
import jdk_proxy.impl.MathUtilImpl;
import org.junit.Test;

public class MyTest {

    /**
     * 基于java中提供的proxy工具类，实现的动态代理
     *
     * spring aop其中底层就是根据此实现aop功能， 该方法有缺陷， 即当java类没有实现接口的时候，无法创建出动态代理对象
     */
    @Test
    public void test01(){
        MathUtil mathUtil = MathProxyFactory.getProxy(new MathUtilImpl());
        int add = mathUtil.add(0, 2);
    }
}
