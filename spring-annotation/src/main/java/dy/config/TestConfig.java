package dy.config;

import dy.component.conditons.LinuxCondition;
import dy.component.conditons.WindowsCondition;
import dy.component.filters.TestFilter;
import dy.component.imports.TestBeanDefinitionRegistrar;
import dy.component.imports.TestImportSelector;
import dy.entities.Person;
import dy.service.TestImportService;
import dy.service.TestService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

// 快速导入一个组件，使用数组即可一次性导入多个组件
// 导入ImportSelector接口的实现类可以一次性导入多个组件（导入组件为接口返回的string数组）
// 导入ImportBeanDefinitionRegistrar接口的实现类，可以用于直接向ioc容器种注册组件
@Import({TestImportService.class, TestImportSelector.class, TestBeanDefinitionRegistrar.class})

// 相当于 <context:component-scan base-package=""/> 的xml 配置, 此种导入bean置入ioc容器的方式适用于自己创建的类的情况
@ComponentScan(
        value = "dy",   // 指定要扫描的包

        excludeFilters = {  // 指定要排除的组件（将符合filter匹配的组件进行排除）
            @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class}),   // 不扫描 Repository 注解的类，按照注解类型进行过滤
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TestService.class}), // 不扫描 TestService 类，按照bean的类型进行过滤
            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {TestFilter.class})   // 自定义是否匹配规则
        },
        includeFilters = {  // 指定需要加入的组件（将符合filter匹配规则的组件进行加入）

        }
        // useDefaultFilters = false 默认为true 即会将扫描包下的所有组件加入ioc容器，设置为false + includeFilters 可实现只向ioc容器中2加入指定组件的功能
)

@PropertySource(value = "classpath:testprop.properties")

// 该注解告诉spring 该类 等于 配置版的 applicationContext.xml
@Configuration
public class TestConfig {

    // 1. 注册bean --- 方法一
    // 等同于 <bean class='xxx'/> 的注解，返回类型即为注册的bean, bean名称即为方法名
    // @Scope("xxx") 指定bean的scope属性，等同于xml配置中的scope属性
    //@Scope("prototype") // prototype属性为多实例， singleton 为单实例的bean
    @Lazy   // 等同于xml中的lazy属性，针对单实例bean，作用为设置懒加载
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Person person(){
        Person person = new Person();

        person.setName("p-111");
        person.setAge(12);

        return person;
    }

    /**
     * @Conditional(WindowsCondition.class) 注解
     *      传入 Condition 接口的实现类， 若符合此条件（即condition接口实现类的matches方法返回true）
     *      则会注入该组件，否则不会注入此组件
     * @return
     */
    @Bean
    @Conditional(WindowsCondition.class)
    public Person bill(){
        Person person = new Person();

        person.setName("Bill Gates");
        person.setAge(60);

        return person;
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public Person linus(){
        Person person = new Person();

        person.setName("Linus Torvalds");
        person.setAge(60);

        return person;
    }

    @Primary
    @Bean
    public Person primaryPerson(){
        return new Person();
    }

}
