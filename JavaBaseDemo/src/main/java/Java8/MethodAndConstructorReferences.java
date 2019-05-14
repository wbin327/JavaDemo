package Java8;

/**
 * 方法和构造函数引用
 * 与Python中，直接将函数作为参数类似
 * 下面的例子就是将Person类的构造函数与接口的抽象函数create(String firstName, String lastName)关联
 */
public class MethodAndConstructorReferences {
    public static void main(String[] args){
        // 使用构造函数引用将构造函数和接口关联，而不是手动实现一个工厂类
        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }
}

class Person{
    String firstName;
    String lastName;

    Person(){}

    Person(String fistName, String  lastName){
        this.firstName = fistName;
        this.lastName = lastName;
    }
}

// 创建Person对象的对象工厂接口
interface PersonFactory{
    Person create(String firstName, String lastName);
}
