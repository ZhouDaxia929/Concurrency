package Chapter2;

/**
 * P51
 *
 * @author ZhouDaxia
 * @create 2017-02-20-12:42
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while(!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(100);
        number = 42;
        ready = true;
        Thread.sleep(100);
    }
}