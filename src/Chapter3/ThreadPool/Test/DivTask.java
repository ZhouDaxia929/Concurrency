package Chapter3.ThreadPool.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * P113
 *
 * @author ZhouDaxia
 * @create 2017-03-04-21:51
 */
public class DivTask implements Runnable {
    int a, b;
    public DivTask(int a, int b){
        this.a = a;
        this.b = b;
    }
    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //pools.submit(new DivTask(100, i));  //除0导致的输出结果错误，但是没有错误提示
            pools.execute(new DivTask(100, i));
        }
    }
}