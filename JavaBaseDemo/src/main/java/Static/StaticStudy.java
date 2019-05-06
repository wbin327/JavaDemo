package Static;

/**
 * 静态块，静态变量，构造块，构造函数执行顺序
 */
public class StaticStudy {
    public static String text = "静态变量";   //静态变量

    public StaticStudy(){
        // 构造函数
        System.out.println("构造函数");
    }

    // 构造块
    {System.out.println("构造块");}

    // 静态块
    static{
        System.out.println("静态块");
    }

    public static void main(String[] args){
        new StaticStudy();
    }
}
