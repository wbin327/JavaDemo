package Java8;

/**
 * Java 8，函数式接口
 * 函数式接口：是指仅仅只包含一个抽象方法，但是可以有多个非抽象方法（接口中的默认方法）的接口
 * @FunctionalInterface:这个注解不是必须的，只要接口只包含一个抽象方法，虚拟机会自动判断该接口为函数式接口
 *
 */
public class FunctionalInterfaces {
    public static void main(String[] args){
        Formula formula = Math::sqrt;
        System.out.println(formula.calculate(16));
        System.out.println(formula.sqrt(16));
    }
}

@FunctionalInterface
interface Formula{
    double calculate(int a);
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}

