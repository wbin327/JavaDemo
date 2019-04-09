package ThreadStudy;

/**
 * JAVA创建线程的方法有以下两种
 * 1.继承Thread类，实现run方法（不推荐，因为JAVA不支持多继承，如果后续程序需要继承新类，则需要重写代码）
 * 2.实现Runable接口
 * 3.通过callable和future创建线程
 */
public class RunnableDemo implements Runnable{
    private String threadName;
    RunnableDemo(String threadName){
        this.threadName = threadName;
    }
    public void run() {
        for(int i=0; i<20; i++)
            System.out.println("当前执行的线程为：" + this.threadName);
    }

    public static void main(String[] args){
        // 使用匿名类创建RunnableDemo实例
        Thread t1 = new Thread(new RunnableDemo("线程1"));
        Thread t2 = new Thread(new RunnableDemo("线程2"));
        t1.start();
        t2.start();
    }
}
