package dy.entities;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    @Value("${person.name}")
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void init(){
        System.out.println("initMethod execute ---");
    }

    public void destroy(){
        System.out.println("destroyMethod execute ---");
    }
}
