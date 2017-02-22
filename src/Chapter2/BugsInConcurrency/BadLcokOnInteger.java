package Chapter2.BugsInConcurrency;

/**
 * P66
 *
 * @author ZhouDaxia
 * @create 2017-02-21-19:54
 */
public class BadLcokOnInteger implements Runnable {  //fe
    public static Integer i = 0;
    static BadLcokOnInteger instance = new BadLcokOnInteger();

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (i){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}