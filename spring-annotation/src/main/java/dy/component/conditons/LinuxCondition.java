package dy.component.conditons;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        // 获取到ioc容器创建使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();

        // 获取到类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();

        // 获取当前运行环境
        Environment environment = conditionContext.getEnvironment();

        // 获取到当前bean定义的注册信息
        BeanDefinitionRegistry registry = conditionContext.getRegistry();


        String property = environment.getProperty("os.name");

        if(property.equalsIgnoreCase("linux")){
            return true;
        }

        return false;
    }
}
