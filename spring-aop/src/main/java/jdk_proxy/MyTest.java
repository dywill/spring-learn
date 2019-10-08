package jdk_proxy;

import jdk_proxy.factory.MathProxyFactory;
import jdk_proxy.impl.MathUtilImpl;

public class MyTest {

    public  void test1() {

        MathUtil mathUtil1 = MathProxyFactory.getProxy(new MathUtilImpl());
        int add = mathUtil1.add(1, 2);
    }
}
