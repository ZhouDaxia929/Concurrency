package Chapter2;

/**
 * P43
 *
 * @author ZhouDaxia
 * @create 2017-02-20-10:54
 */
public class SimpleWN {
    final static Object object = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + "T1 start! ");
                try{
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object ");
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + "T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try{
                    Thread.sleep(2000); //尽管已经通知了唤醒线程，但是会强制等待2秒，因为此时T2还没有释放对象锁。
                }catch (InterruptedException e){

                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}