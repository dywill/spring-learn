package dy.component.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("testBeanFactory")
public class TestBeanFactoryt implements FactoryBean<TestAnimal> {

    /**
     * 工厂bean的注册bean
     * @return
     * @throws Exception
     */
    public TestAnimal getObject() throws Exception {
        return new TestAnimal();
    }

    /**
     * 注册bean的类型
     * @return
     */
    public Class<?> getObjectType() {
        return TestAnimal.class;
    }

    /**
     * 是否单列
     * @return
     */
    public boolean isSingleton() {
        return true;
    }
}
