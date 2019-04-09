package ThreadStudy;

/**
 * 指令重排
 * 指令重排是指编译器自动优化执行顺序，将本应在下一句才执行的代码提前执行，目的在于提高性能
 * 在多线程环境下有可能导致错误的结果，例如下面这个例子
 *
 * 这里得先了解一下，每一行程序执行的流程
 * 1.找到相应的指令
 * 2.从主存中读取数据到线程所处的内存空间（每个线程的内存空间是独立的）
 * 3.执行指令
 * 4.将执行结果写回主存
 *
 * 下面这个例子，就有可能发生，执行结果未写回主存就执行了下一行语句，导致结果与预期不符合
 */
public class HappenBefore {
    private static int a=0;
    private static boolean flag=false;

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<100;i++) {
            a=0;
            flag = false;
            // 线程1读取数据
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            //线程2修改数据
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }
                if (a == 0) {
                    System.out.println("happen before a-》" + a);
                }
            });

            t1.start();
            t2.start();

            // 先让t1执行再让T2执行
            t1.join();
            t2.join();
        }
    }
}
