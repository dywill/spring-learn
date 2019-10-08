package spring_aop.myaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面类
 */
@Aspect
@Component
public class MyAspect {

    /**
     * 通知方法， 此处为前置 通知方法
     *
     * @param joinPoint
     */
    @Before("execution(* spring_aop.util.DateUtil.*(..))")      // 此处为切入点表达式
    public void beforeMethod(JoinPoint joinPoint){  // 连接点
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("[spring aop before]" + name + "方法执行， 其参数为：" + Arrays.asList(args));
    }

    @Pointcut("execution(* spring_aop.util.DateUtil.*(..))")    // 抽取出来的切人点表达式，方便多个通知方法进行重复调用
    public void pointCut(){};

    /**
     * 此为 后置通知， 相当于finally语句块中的执行内容
     */
    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("[spring aop after]" + name + "方法，最终执行结束");
    }

    /**
     *  此为方法完成通知，在目标方法执行结束后执行， 若目标方法执行抛出异常，则不会执行该方法
     */
    @AfterReturning(value = "pointCut()", returning = "res")
    public void afterReturningMethod(JoinPoint joinPoint, Object res){
        String name = joinPoint.getSignature().getName();
        System.out.println("[spring aop afterReturning]" + name + "方法执行完成，执行结果：" + res);
    }

    /**
     * 异常通知， 相当于 catch 语句块中执行的内容
     */
    @AfterThrowing(value = "pointCut()",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println("[spring aop afterThrowing]" + name + "方法执行出项异常，异常信息：" + ex);
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Exception{  // 可执行的连接点

        Object[] args = pjd.getArgs();
        String name = pjd.getSignature().getName();

        try {
            //before
            System.out.println("[spring around]" + name + "方法执行， 执行参数为：" + Arrays.asList(args));
            Object proceed = pjd.proceed(args); //等于 method.invoke()
            System.out.println("[spring around]" + name + "方法执行完成， 执行结果为：" + proceed);
            return proceed;
        } catch (Throwable throwable) {
            System.out.println("[spring around]" + name + "方法执行异常， 异常信息为：" + throwable);
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("[spring around]" + name + "方法执行结束");
        }
    }
}
