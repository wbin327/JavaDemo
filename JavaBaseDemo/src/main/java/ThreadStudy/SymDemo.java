package ThreadStudy;

/**
 * 线程安全(synchronized)例子
 * 1.synchronized关键字可以修饰方法，称之为同步方法
 * 2.synchronized关键字还可修饰一个代码块，称之为同步块，语法：synchronized(obj){ }
 */

public class SymDemo {
    // 车票数量
    private static int num = 10;

    public synchronized void buy(){
        if(this.num > 0) {
            this.num--;
            System.out.println("还是剩余车票："+ this.num);
        }
        else{
            System.out.println("车票已抢空，无法购买");
        }
    }
    public int getNum(){
        return this.num;
    }

    public static void main(String[] args){
        SymDemo sd = new SymDemo();
        Thread t1 = new Thread(new Consumer2(sd));
        Thread t2 = new Thread(new Consumer2(sd));
        t1.start();
        t2.start();
    }
}

class Consumer2 implements Runnable{
    private SymDemo sd;
    Consumer2(SymDemo sd) {
        this.sd = sd;
    }

    @Override
    public void run() {
        while(true) {
            if (sd.getNum() > 0)
                sd.buy();
            else
                break;
        }
    }
    //
}
