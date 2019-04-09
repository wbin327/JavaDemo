package ThreadStudy;

/**
 * JAVA线程有如下六中种状态，其中BLOCKED,WAITING,TIMED_WAITING都是属于阻塞态
 * 1.NEW,尚未启动的线程处于此状态。
 * 2.RUNNABLE ,在Java虚拟机中执行的线程处于此状态。
 * 3.BLOCKED ,被阻塞等待监视器锁定的线程处于此状态,如IO操作。
 * 4.WAITING ,正在等待另一个线程执行特定动作的线程处于此状态,如sleep()。
 * 5.TIMED_WAITING ,正在等待另一个线程执行动作达到指定等待时间的线程处于此状态,如join()。
 * 6.TERMINATED ,已退出的线程处于此状态。
 */
public class AllState {

    public static void main(String[] args){
        Thread t = new Thread(()-> {
            for(int i=0;i<10;i++)
                System.out.println("....");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
        });
        // 观察状态,NEW
        System.out.println(t.getState());

        //RUNNABLE
        t.start();
        System.out.println(t.getState());

        //timed_waiting
        while(t.getState() != Thread.State.TERMINATED){
            System.out.println(t.getState());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // TERMINATED
        System.out.println(t.getState());

    }
}
