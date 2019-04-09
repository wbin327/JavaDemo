package ThreadStudy;

/**
 * 实现可重入锁和不可重入锁
 */
public class LockDemo {

    public static void main(String[] args){
        //Lock lock = new Lock();
        Rlock lock = new Rlock();
        Thread t1 = new Thread(new testThread(lock), "测试线程一号（不可重入锁）");
        t1.start();
    }
}

class testThread implements Runnable{
    private Lock lock;

    testThread(Lock lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        // 申请锁
        this.lock.lock();
        // 执行操作
        System.out.println("线程："+Thread.currentThread().getName() + "，我申请到锁了，接下来我要再次申请一遍锁");
        // 再次申请锁，不可重入锁的话，这里就会申请失败，导致线程一直阻塞
        this.lock.lock();
        System.out.println("线程："+Thread.currentThread().getName() + "，我再次申请到锁了");
    }
}

//不可重入锁
class Lock{
    // 是否占用
    protected boolean isLock = false;
    public synchronized void lock(){
        if(this.isLock) {
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else{
            this.isLock = true;
        }
    }

    public synchronized void unLock(){
        if(this.isLock){
            System.out.println("不可重入锁已上锁，正在为其执行解锁操作");
            this.isLock = false;
            // 解说后通知正在等待本对象的线程
            notifyAll();
        }
        else{
            System.out.println("不可重入锁已解锁，不需要执行解锁操作");
        }
    }
}

//可重入锁
class Rlock extends Lock{
    // 用于保存申请了锁的对象
    private Thread thread;
    // 计数器，用于记录重入锁次数
    private int count = 0;


    @Override       //重写lock方法
    public synchronized void lock(){
        // 如果已上锁，并且上锁的线程不是当前线程，线程切换成阻塞态
        if(this.isLock && this.thread != Thread.currentThread()){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else{
            this.isLock = true;
            this.thread = Thread.currentThread();
            this.count ++;
        }
    }

    @Override
    public synchronized void unLock(){
        if(this.thread == Thread.currentThread() && this.isLock)
            this.count --;
        else {
            System.out.println("当前线程并未获得锁，无法执行unlock");
            return;
        }
        if(this.count == 0){
            this.thread = null;
            this.isLock = false;
            notifyAll();
        }
    }

}
