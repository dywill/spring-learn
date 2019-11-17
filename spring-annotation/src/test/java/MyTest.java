import dy.config.TestConfig;
import dy.entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class MyTest {

    ApplicationContext applicationContext = null;

    @Before
    public void init(){

        // 注解版驱动的spring 通过此实现类创建spring容器
        applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    @After
    public void destroy(){
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    @Test
    public void test001(){
//        Person bean = applicationContext.getBean(Person.class);

        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("os ======>" + property);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test002(){
        /**
         * 工厂bean默认使用getObject方法注册bean
         *  若在注册名前添加 & ，则为获取到bean工厂本身
         */
        Object bean = applicationContext.getBean("testBeanFactory");
        Object beanFactory = applicationContext.getBean("&testBeanFactory");

        System.out.println(bean.getClass().getName());
        System.out.println(beanFactory.getClass().getName());
    }

    @Test
    public void test003(){
        /**
         * 通过@Primary指定了优先获取组件
         */
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean.getName());
    }
}
