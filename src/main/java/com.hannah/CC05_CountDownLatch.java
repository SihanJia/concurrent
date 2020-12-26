package com.hannah;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CC05_CountDownLatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(20);
        List<Thread> threads = new ArrayList<>(100);
        for(int i=0;i<100;i++) {
            threads.add(new Thread(()-> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
                System.out.println(latch.getCount());
            },""+i));
        }
        threads.forEach(t-> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch end");
    }
}
