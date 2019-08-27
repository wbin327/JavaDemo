package ThreadStudy;

/**
 * join()方法学习
 * t.join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；通常用于在main()主线程内，
 * 等待其它线程完成再结束main()主线程
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()-> {
            try {
                System.out.println("线程休眠两秒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        // 主线程等待t1线程执行完毕后再结束
        t1.join();
        System.out.println("主线程结束");
    }
}
