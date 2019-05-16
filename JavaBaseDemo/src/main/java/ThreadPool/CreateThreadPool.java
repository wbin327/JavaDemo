package ThreadPool;

import java.util.concurrent.*;

/**
 * 线程池
 * 在《阿里巴巴java开发手册》中指出了线程资源必须通过线程池提供，不允许在应用中自行显示的创建线程，
 * 这样一方面是线程的创建更加规范，可以合理控制开辟线程的数量；
 * 另一方面线程的细节管理交给线程池处理，优化了资源的开销。
 * 而线程池不允许使用Executors去创建，而要通过ThreadPoolExecutor方式
 *
 * ThreadPoolExecutor构造函数：
 *     public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler)
 *     参数说明：
 *     corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
 *     maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
 *     keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
 *     unit:keepAliveTime的单位
 *     workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
 *               直接提交队列：SynchronousQueue，SynchronousQueue是一个特殊的BlockingQueue，它没有容量，
 *                             每执行一个插入操作就会阻塞，需要再执行一个删除操作才会被唤醒，
 *                             反之每一个删除操作也都要等待对应的插入操作。
 *               有界任务队列：ArrayBlockingQueue,若有新的任务需要执行时，线程池会创建新的线程，直到创建的线程数量达到corePoolSize时，
 *                             则会将新的任务加入到等待队列中。若等待队列已满，即超过ArrayBlockingQueue初始化的容量，
 *                             则继续创建线程，直到线程数量达到maximumPoolSize设置的最大线程数量，若大于maximumPoolSize，则执行拒绝策略。
 *               无界任务队列：LinkedBlockingQueue，线程池的任务队列可以无限制的添加新的任务，而线程池创建的最大线程数量
 *                             就是你corePoolSize设置的数量，也就是说在这种情况下maximumPoolSize这个参数是无效的
 *               优先任务队列：PriorityBlockingQueue，它其实是一个特殊的无界队列，它其中无论添加了多少个任务，
 *                             线程池创建的线程数也不会超过corePoolSize的数量，只不过其他队列一般是按照先进先出的规则处理任务，
 *                             而PriorityBlockingQueue队列可以自定义规则根据任务的优先级顺序先后执行。
 *     threadFactory:线程工厂，用于创建线程，一般用默认即可；
 *     handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务，有以下几种解决策略
 *             AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
 *             CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
 *             DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，
 *                                       马上要被执行的那个任务，并尝试再次提交；
 *             DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
 *             自己定义拒绝策略：实现RejectedExecutionHandler接口
 *
 */
public class CreateThreadPool {
    /**
     * 这里创建了一个线程池
     * corePoolSize 1:线程池中首先创建一个线程
     * maximunPoolSize 2:最大线程数量为2，如果用于执行任务的线程数量小于maximumPoolSize，则尝试创建新的进程，
     *                   如果达到maximumPoolSize设置的最大值，则根据你设置的handler执行拒绝策略。
     * keepAliveTime 1000: 当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
     * unit TimeUnit.MILLISECONDS: keepAliveTime的单位为毫秒
     * workQueue new SynchronousQueue<Runnable>(): 任务队列，使用该队列，提交的任务不会被保存，总是马上提交
     * threadFactory Executors.defaultThreadFactory: 默认的线程工厂
     * handler new ThreadPoolExecutor.AbortPolicy(): AbortPolicy是一种拒绝策略，该策略会直接抛出异常
     *
     * 因为下面的程序有三个任务，而最大线程数量为2，超出了最大线程数量，并且由于SynchronousQueue,提交的任务不会被保存，总是会马上提交执行
     * 但是由于最大只有两个线程，所以只能执行拒绝策略，抛出异常
     */
    static void synchronousQueue(){
        ExecutorService pool = new ThreadPoolExecutor(1,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for(int i=0; i<3; i++){
            pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    /**
     * 优先级任务队列 + AbortPolicy策略
     * 优先级任务队列是一个特殊的无界任务队列，线程池的任务队列可以无限制的添加新的任务，
     * 而线程池创建的最大线程数量就是你corePoolSize设置的数量
     * 若后续有新的任务加入，则直接进入队列等待，当使用这种任务队列模式时，一定要注意你任务提交与处理之间的协调与控制，
     * 不然会出现队列中的任务由于无法及时处理导致一直增长，直到最后资源耗尽的问题。
     */
    static void priorityBlockingQueue(){
        ExecutorService pool = new ThreadPoolExecutor(1,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<10;i++) {
            pool.execute(new ThreadTask(i));
        }
    }



    public static void main(String[] args){
        //synchronousQueue();
        priorityBlockingQueue();
    }
}

class ThreadTask implements Runnable,Comparable<ThreadTask>{

    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadTask() {

    }

    public ThreadTask(int priority) {
        this.priority = priority;
    }

    //当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1,值越小优先级越高
    public int compareTo(ThreadTask o) {
        return  this.priority>o.priority?-1:1;
    }

    public void run() {
        try {
            //让线程阻塞，使后续任务进入缓存队列
            Thread.sleep(1000);
            System.out.println("priority:"+this.priority+",ThreadName:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
