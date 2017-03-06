package Chapter3.ForkAndJoin;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

/**
 * P119
 *
 * @author ZhouDaxia
 * @create 2017-03-06-21:20
 */
public class CountTask extends RecursiveTask<Long> { //设置返回值类型为Long
    private static final int THRESHOLD = 10000; //任务分解规模，如果单个任务中总数大于该值，再次进行分解
    private long start;
    private long end;

    public CountTask(long start, long end){
        this.start = start;
        this.end = end;
    }

    public Long compute(){
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if(canCompute){ //如果任务本身小于设定值，则直接进行单线程运算
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        }
        else{
            //分成100个小任务
            long step = (start + end) / 100; //得出步进值
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if(lastOne > end)
                    lastOne = end;
                CountTask subTask = new CountTask(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork(); //提交任务
            }
            for(CountTask t : subTasks){
                sum += t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        long sum = 0;
        for(long i = 0; i <= 200000L; i++){
            sum += i;
        }
        long time2 = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("yihaoshi:" + (time2 - time1));

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try{
            long res = result.get(); //执行get方法获得最终结果，如果get方法没有结束，会在这里进行等待
            System.out.println("sum=" + res);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("erhaoshi:" + (System.currentTimeMillis() - time2));

        Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
    }
}