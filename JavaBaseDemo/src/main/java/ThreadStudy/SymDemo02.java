package ThreadStudy;
/**
 * 线程安全(synchronized)例子
 * 1.synchronized关键字可以修饰方法，称之为同步方法
 * 2.synchronized关键字还可修饰一个代码块，称之为同步块，语法：synchronized(obj){ }
 */

public class SymDemo02 {
    // 车票数量
    static int num = 100;

    public static void main(String[] args){
        SymDemo02 sd = new SymDemo02();
        Thread t1 = new Thread(new Consumer3(sd));
        Thread t2 = new Thread(new Consumer3(sd));
        t1.start();
        t2.start();
    }
}

class Consumer3 implements Runnable{
    private SymDemo02 sd;
    Consumer3(SymDemo02 sd) {
        this.sd = sd;
    }

    private String buy(){
        if(this.sd.num <= 0) {
            System.out.println("车票已抢空，无法购买");
            return "fail";
        }
        else{
            synchronized (this.sd){
                if(this.sd.num > 0) {
                    System.out.println("购买前车票剩余：" + this.sd.num  );
                    this.sd.num--;
                    return"success";
                }else{
                    System.out.println("车票已抢空，无法购买");
                    return"fail";
                }
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            if(this.buy().equals("success"))
                continue;
            else
                break;
        }
    }
    //
}

