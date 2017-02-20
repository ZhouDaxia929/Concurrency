package Chapter2;

/**
 * P36
 *
 * @author ZhouDaxia
 * @create 2017-02-19-23:16
 */
public class StopThreadUnsafe {
    public static class User{
        private int id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {

            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public User(){
            id = 0;
            name = "0";
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static User u = new User();

//    public static class ChangeObjectThread extends Thread{
//        @Override
//        public void run() {
//            while(true){
//                synchronized (u){
//                    int v = (int)(System.currentTimeMillis() / 1000);
//                    u.setId(v);
//                    //Oh, do sth. else
//                    try{
//                        Thread.sleep(100);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    u.setName(String.valueOf(v));
//                }
//                Thread.yield();
//            }
//        }
//    }

    //更新后的ChangeObjectThread类的正确写法
    public static class ChangeObjectThread implements Runnable{
        volatile boolean stopme = false;
        public void stopMe(){
            stopme = true;
        }

        @Override
        public void run() {
            while(true){
                if(stopme){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while(true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName()))
                        System.out.println(u.toString());
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new ReadObjectThread().start();
        int i = 0;
        while(i++ < 100){
            Thread t = new Thread(new ChangeObjectThread());
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}