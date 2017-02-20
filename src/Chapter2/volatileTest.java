package Chapter2;

/**
 * P51
 *
 * @author ZhouDaxia
 * @create 2017-02-20-12:29
 */
public class volatileTest {
    static volatile int i = 0;
    static PlusTask pt = new PlusTask();
    public static class PlusTask implements Runnable{

//        @Override
//        public void run() {
//            synchronized (pt){
//                for (int k = 0; k < 10000; k++)
//                    i++;
//            }
//
//        }
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++)
                i++;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(i);
    }


//    public static void main(String[] args) throws InterruptedException{
//        Thread[] threads = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new Thread(pt);
//            threads[i].start();
//        }
//        for (int i = 0; i < 10; i++) {
//            threads[i].join();
//        }
//        System.out.println(i);
//    }
}