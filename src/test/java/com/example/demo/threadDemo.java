package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：ghd
 * @date ：Created in 2019-09-24 10:00
 * @description：
 * @modified By：
 * @version: $
 */
public class threadDemo {

    private static final int threadCount = 5;
    private static AtomicInteger atomicInteger = new AtomicInteger();

    class ConsumerThread extends Thread{
        private BlockingQueue queue;
        private volatile boolean flag=true;

        public ConsumerThread(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("消费者线程启动。。。。。。。");
            try{
                while (flag){
                    //获取完毕后，队列会删除头元素
                    String data = (String) queue.poll(2, TimeUnit.SECONDS);
                    if(data!=null){
                        System.out.println(Thread.currentThread().getName()+ "--> " + "消费者获取data："+data+"成功！");
                    } else{
                        System.out.println("消费者获取data失败！");
                        this.flag=false;
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("消费者线程停止。。。。。。。。");
            }
        }
        public void stopThread(){
            this.flag=false;
        }
    }

    public void start() throws InterruptedException {
        final BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

        deque.putFirst("a");
        deque.putFirst("b");
        deque.putFirst("c");
        deque.putFirst("d");
        deque.putFirst("e");
        deque.putFirst("e");
        deque.putFirst("e");
        deque.putFirst("e");

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            ConsumerThread c1= new ConsumerThread(deque);
            pool.submit(c1);
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        new threadDemo().start();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
    }
}
