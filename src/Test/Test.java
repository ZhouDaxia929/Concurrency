package Test;

/**
 * 一些测试相关的代码
 *
 * @author ZhouDaxia
 * @create 2017-02-19-23:55
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interruted!");
                        break;
                    }
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("Interruted When Sleep");
                        //设置中断状态
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}