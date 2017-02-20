package Chapter2;

/**
 * P49
 *
 * @author ZhouDaxia
 * @create 2017-02-20-12:09
 */
public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        //Thread.sleep(2);
        at.join();
        System.out.println(i);
    }
}