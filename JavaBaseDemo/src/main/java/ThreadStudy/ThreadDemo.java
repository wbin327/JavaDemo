package ThreadStudy;

/**
 * JAVA创建线程的方法有以下两种
 * 1.继承Thread类，实现run方法（不推荐，因为JAVA不支持多继承，如果后续程序需要继承新类，则需要重写代码）
 * 2.实现Runable
 * 3.通过callable和future创建线程
 */
public class ThreadDemo extends Thread{
    ThreadDemo(String threadName){
        super(threadName);
    }
    @Override
    public void run(){
        for(int i=0; i<20; i++)
            System.out.println("当前执行的线程为：" + super.getName());
    }

    public static void main(String[] args){
        ThreadDemo st = new ThreadDemo("线程1");
        ThreadDemo st2 = new ThreadDemo("线程2");
        // 启动线程
        st.start();
        st2.start();
    }
}
