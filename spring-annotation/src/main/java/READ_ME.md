### 基于注解的Spring
>>> 注解的spring应当同配置的spring互相对照看
---
+ 注册bean的方式
    + 通过@Bean注解添加组件
        + @Lazy @Scope 注解指定bean的 是否单例 是否懒加载
        + 通过和 @Conditional 注解相结合，来实现依据条件向容器中添加组件
        + 指定注解中 initMethod destroyMethod 属性来定制bean的生命周期 
    + 通过@ComponentScan注解 + @Component 向容器中添加组件
        + value属性为指定包扫描的包名
        + excludeFilters 属性为指定排除的组件
        + includeFilters 属性为指定包含的组件
        + Filter 注解为指定过滤的规则（例如添加到 excludeFilters 中，即为满足规律规则的组件则排除）
    + 通过@Import注解添加组件
        + value 为class数组，添加普通的类则为直接添加组件
        + value 为 ImportSelector 接口实现类，则为添加接口返回String[] 数组的所有类
        + value 为 ImportBeanDefinitionRegistrar 接口实现类时，容器初始化，会调用接口实现方法向容器中添加自定义组件
        >>> 其中第一种适合各种情况下向容器中添加组件（缺点为相较其他两种方法略显麻烦），第二种方式更加适合于向容器中添加自己编写的组件（这种方式显然比@Bean更加方便），而当导入jar包中的组件时，若只是简单调用无参构造器，使用@Bean稍显麻烦，故可以采取@Import导入类的方式快速向容器中添加组件 
    + 使用FactoryBean来注册组件

---

+ bean的生命周期
    + 通过注解指定初始化和销毁时的方法。即设置@Bean注解中对应的 initMethod() 等的方法名
    + 实现InitializingBean和DisposableBean接口来管理生命周期
    + 通过@PostConstruct，@PreDestroy两个注解来管理生命周期
    + 通过BeanPostProcessor接口来管理bean的生命周期

---

+ bean属性赋值
    + 通过@Value注解向spring组件的属性赋值
    
            1. @Value注解可以填入基本数据类型的值
            2. 可以使用Spel表达式，如#{80 - 2}
            3. 可以使用${xxx}来获取运行时环境变量的值
    
        >>>相当于<bean>标签中value属性 
    + 在配置类上使用@PropertySource来指定要导入的配置文件
        >>>相当于xml配置中的<context:property-placeholder location="xxx"/>标签
---

+ 自动装配bean
    + 通过@Autowired注解
    
            1. 该注解优先按类型进行装配，若有多个类型相同的注册bean，则优先使用注册名与属性名相同
            2. 当有多个同类型的组件时，优先注入含有@Primary注解的组件
            3. @Qualifier("id") 可以指定自动装配使用的bean的id
            4. 可以通过将@Autowired中的require属性设置为false，可以设置不强制装配
            5. @Autowired注解可标注在set方法，构造器方法或者其参数位置上
            7. 若组件中仅有一个有参构造器，则默认该构造器上由@Autowird注解
        >>> @Autowired注解的实现原理， 通过bean后处理器（AutowiredAnnotationBeanPostProcessor）实现， xxxAware接口，都会有对应xxxAwareProcessor（BeanPostProcessor子接口）
    + 通过@Resource （jsr250规范）注解 --- 一般不使用来获取到参数，并调用回调方法
    + 通过@Inject （jsr330规范） 注解 --- 一般不使用
    + 自定义组件若想装配使用spring底层使用的相关组件时，可实现对应Aware接口下的子接口，实现回调的set方法即可