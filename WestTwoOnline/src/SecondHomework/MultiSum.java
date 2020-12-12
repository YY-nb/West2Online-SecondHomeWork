package SecondHomework;


import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.currentTimeMillis;

//每个线程累加得到的数封装成一个类
class eachSum{
    long value=0;

    public eachSum(long value) {
        this.value = value;
    }
}

public class MultiSum implements Runnable{
    private eachSum ans;
    private long begin;    //每个分区的起始数
    private long end;     //每个分区的最后一个数
    private int targetNum;

    private static CountDownLatch latch;    //CountDownLatch 用于等待所有分支线程执行完
    public MultiSum(eachSum ans, long begin, long end, int targetNum, int threads) {  //threads是创建的线程数
        this.ans = ans;
        this.begin = begin;
        this.end = end;
        this.targetNum = targetNum;

        latch=new CountDownLatch(threads);
    }

    @Override
    public void run() {
        for(long i=begin;i<=end;i++){
            if (contain(i, targetNum)) this.ans.value += i;
        }
        latch.countDown();  //执行完上面的for让latch 里的计数器减一，说明该分支线程已执行结束

    }
    private static boolean contain(long num, int x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }
    public static void main(String[] args) {
        long max=1000000000; //1000000000
        long sum=0;
        int threads=16; //线程数
//使用数组保存每一个线程算出来的和，用对象保存是因为对象中的sum在累加时会不断变化，但是对象的引用不会变，到时候只要通过指向对象的引用来取出每一分区的和
        eachSum [] sumArr=new eachSum[threads];


        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        long beginTime=System.currentTimeMillis();
        //对对象数组进行初始化
        for(int i=0;i<threads;i++){
            sumArr[i]=new eachSum(0);
        }
        for(int i=0;i<threads;i++){
            long begin=max*i/threads+1; //计算分区的起始数
            long end=max*(i+1)/threads;   //计算分区的最后一个数
            new Thread(new MultiSum(sumArr[i], begin,end,x,threads)).start();   //启动线程
        }
        try {

            latch.await();   //阻塞当前线程直至latch中的值减到0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       //此时所有分支线程已执行完毕，可以开始对每一个分支线程的计算结果进行累加
        for(int i=0;i<threads;i++){
            sum+=sumArr[i].value;
        }
        System.out.println(sum);
        long endTime=System.currentTimeMillis();
        System.out.println("程序花了 "+(endTime-beginTime)/1000+" s");
    }

}

