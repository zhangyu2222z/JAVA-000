package com.zy.homework1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
 *
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-11-09 15:05
 **/
public class Custom {

    public static void main(String[] args) throws Exception{

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
//        int result = sum(); //这是得到的返回值
        // 确保  拿到result 并输出

        // 方式：
        // 1、
//        CustomThread a = new CustomThread();
//        a.run();
//        a.join();
        // 2、
//        CustomThread a = new CustomThread();
//        a.run();
//        a.wait();
        // 3、
//        CustomThread a = new CustomThread();
//        a.run();
//        a.interrupt();
        // 4、
//        CustomCountDownThread customCountDownThread = new CustomCountDownThread(new CountDownLatch(1));
//        customCountDownThread.run();
        // 5、
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit(() -> {
//            int result = Custom.sum();
//            System.out.println("异步计算结果为："+result);
//        });
//        exec.shutdown();
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            int result = Custom.sum();
            System.out.println("异步计算结果为："+result);
        }
    }

    static class CustomCountDownThread implements Runnable{
        private CountDownLatch latch;

        public CustomCountDownThread(){}

        public CustomCountDownThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            int result = Custom.sum();
            latch.countDown();
            System.out.println("异步计算结果为："+result);
        }
    }

}

