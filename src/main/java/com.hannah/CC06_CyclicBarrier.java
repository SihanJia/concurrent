package com.hannah;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CC06_CyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(20,()-> {
            System.out.println("倒数结束");
        });
        for (int i = 0; i < 40; i++) {
            new Thread(()-> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }, "" + i).start();
        }
    }
}
