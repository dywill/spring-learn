package dy.entities;

public class Person {

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
