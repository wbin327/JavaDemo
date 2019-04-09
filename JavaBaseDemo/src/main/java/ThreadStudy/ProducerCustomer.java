package ThreadStudy;


import java.util.ArrayList;
import java.util.List;

/**
 * 经典生产者消费者模式，解决该问题主要有如下两种方法
 * 1.管程法
 * 2.信号灯法
 * 下面这个示例使用的是管程法，主要有三个对象，消费者，生产者，存储数据的容器，算法思路如下
 * 1.生产者往容器中添加数据，当容器存满时，使用wait()挂起线程，并等待消费者消费后唤醒
 * 2.消费者从容器中取数据，当容器为空时，使用wait()挂起线程，并等待生产者生产后唤醒
 */

public class ProducerCustomer {

    public static void main(String[] args){
        Container container = new Container();
        Producer producer = new Producer(container);
        Customer customer = new Customer(container);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(customer);
        t1.start();
        t2.start();

    }
}

// 生产者
class Producer implements Runnable{
    private Container container;

    Producer(Container container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("生产第：" + i + "个产品");
            this.container.add("产品" + i + "号");
        }
    }
}

// 消费者
class Customer implements Runnable{
    private Container container;

    Customer(Container container){
        this.container = container;
    }


    @Override
    public void run() {
        for(int i =0; i<10; i++){
            String s = this.container.get();
            System.out.println("消费：" + s);
        }
    }
}

// 容器，实现一个先进先出的队列
class Container{
    private List<String> list = new ArrayList<>();
    private int length = 10;
    private int head = 0; // 指向队列头部的指针

    public synchronized void add(String s){
        // 队列不满时，才能添加数据
        if(!this.isFull()) {
            this.list.add(s);
            System.out.println("唤醒消费者线程");
            this.notifyAll();
        }
        else{
            // 挂起线程，等待消费者唤醒
            try {
                System.out.println("队列已满，当前队列长度为："+ this.list.size()+ "挂起生产者线程");
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized String get(){
        // 队列不为空时，才能消费数据
        if(!this.isNull()){
            String s = this.list.remove(this.head);
            System.out.println("唤起生产者线程");
            this.notifyAll();
            return s;
        }
        else{
            // 挂起线程，等待生产者唤醒
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                return "队列为空，当前队列长度为："+this.list.size()+"无法消费，挂起消费者线程";
            }
        }
    }

    public Boolean isFull(){
        // 判断队列是否已满
        return this.list.size() == length;
    }

    public Boolean isNull(){
        // 判断队列是否为空
        return this.list.size() == 0;
    }
}