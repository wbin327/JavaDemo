package Java8;

public class LambdaDemo {

    // 内部类
    static class Like2 implements ILike {

        public void lambda(String name) {
            System.out.println(name);
        }
    }

    public static void main(String[] args){
        // 外部类用法
        ILike like = new Like();
        like.lambda("我是外部类");

        // 内部类
        ILike like2 = new Like2();
        like2.lambda("我是内部类");

        // 匿名内部类，JDK8支持使用lambda表达式改写匿名内部类
        ILike like3 = new ILike() {
            // 接口具体实现
            public void lambda(String name) {
                System.out.println(name);
            }
        };
        like3.lambda("我是匿名内部类");

        //lambda表达式,需要注意的点，只有当接口存在唯一一个方法时，才能使用lambda表达式，否则无法通过编译
        // ()内填写的是方法的参数， ->后的内容是函数体
        ILike like4 = (name) -> System.out.println(name);
        like4.lambda("我是lambda表达式");
    }
}

interface ILike{
    void lambda(String name);
}

// 外部类
class Like implements ILike {

    public void lambda(String name) {
        System.out.println(name);
    }
}
