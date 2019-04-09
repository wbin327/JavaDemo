package Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类可以用定时调度的任务，当然也可以使用linux系统自带的crontab命令
 */
public class TimerStudy {

    public static void main(String[] args){
        Timer timer = new Timer();
        MyTask myTask = new MyTask();
        // schedule()方法用于设定任务的执行时间以及重复,
        // delay参数表示该线程在1000毫秒后开始执行，period参数表示间隔1000毫秒再次执行
        timer.schedule(myTask,1000,1000);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
            System.out.println("hello Timer");
            System.out.println(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
    }
}