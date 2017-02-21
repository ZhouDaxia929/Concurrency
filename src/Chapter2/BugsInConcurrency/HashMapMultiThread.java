package Chapter2.BugsInConcurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * P63
 *
 * @author ZhouDaxia
 * @create 2017-02-21-19:43
 */
public class HashMapMultiThread {
    private static Map<String, String> map = new HashMap<>();
    private static class AddThread implements Runnable{
        int start = 0;
        AddThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i++) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMultiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}