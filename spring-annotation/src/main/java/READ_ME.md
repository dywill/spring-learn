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
