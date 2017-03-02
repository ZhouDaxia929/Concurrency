package Chapter3.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * P87
 *
 * @author ZhouDaxia
 * @create 2017-03-02-19:26
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10); //表示需要有10个线程完成任务
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        try{
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            end.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        //等待检查
        end.await(); //要求主线程等待所有10个检查任务全部完成后，才能继续执行。
        //发射火箭
        System.out.println("Fire!");
        exec.shutdown();
    }
}