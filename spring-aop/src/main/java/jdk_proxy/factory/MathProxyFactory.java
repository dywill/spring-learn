package jdk_proxy.factory;

import jdk_proxy.MathUtil;
import jdk_proxy.impl.MathUtilImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MathProxyFactory {


    public static MathUtil getProxy(final MathUtilImpl mathUtil) {

        ClassLoader loader = mathUtil.getClass().getClassLoader();
        Class<?>[] interfaces = mathUtil.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("["+method.getName()+"方法执行开始， 参数："+ Arrays.asList(args) +"]");
                Object res = method.invoke(mathUtil,args);
                System.out.println("["+method.getName()+"方法执行结束， 结果："+ res +"]");
                return res;
            }
        };

        return (MathUtil) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
