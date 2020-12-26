package com.hannah;

import java.util.concurrent.Semaphore;

public class CC09_Semaphor {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 ");
                Thread.sleep(500);
                System.out.println("T1 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 ");
                Thread.sleep(500);
                System.out.println("T2 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
