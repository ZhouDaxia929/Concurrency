package Chapter3.ThreadPool.ScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * P101
 *
 * @author ZhouDaxia
 * @create 2017-03-04-20:05
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，则调度也不会启动
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis() / 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);  //任务处理需要1秒，然后调度周期是2秒，也就是说每2秒钟，任务就会被执行一次。
    }
}