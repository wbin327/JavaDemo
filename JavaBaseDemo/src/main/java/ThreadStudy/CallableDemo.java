package ThreadStudy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * JAVA创建线程的方法有以下两种
 * 1.继承Thread类，实现run方法（不推荐，因为JAVA不支持多继承，如果后续程序需要继承新类，则需要重写代码）
 * 2.实现Runable接口
 * 3.通过callable和future创建线程
 */
public class CallableDemo implements Callable<Boolean> {

    private String threadName;
    CallableDemo(String threadName){
        this.threadName = threadName;
    }
    public Boolean call() throws Exception {
        for(int i=0; i<20; i++)
            System.out.println("当前执行的线程为：" + this.threadName);
        return true;
    }

    public static void main(String[] args) throws Exception{
        CallableDemo cd1 = new CallableDemo("线程1");
        CallableDemo cd2 = new CallableDemo("线程2");

        // 创建执行服务
        ExecutorService es = Executors.newFixedThreadPool(2);
        // 提交执行
        Future<Boolean> result1 = es.submit(cd1);
        Future<Boolean> result2 = es.submit(cd2);
        // 获取结果
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        // 关闭服务
        es.shutdownNow();
    }
}
