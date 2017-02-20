package Chapter2;

/**
 * P55
 *
 * @author ZhouDaxia
 * @create 2017-02-20-14:04
 */
public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while(true){
                synchronized (PriorityDemo.class){  //产生一次资源竞争，使得优先级的差异性体现的明显些
                    count++;
                    if(count > 10000000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;
        @Override
        public void run() {
            while(true){
                synchronized (PriorityDemo.class){
                    count++;
                    if(count > 10000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}