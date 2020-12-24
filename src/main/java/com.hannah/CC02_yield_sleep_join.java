package com.hannah;

public class CC02_yield_sleep_join {
    private static Object obj = new Object();

    public static void main(String[] args) {
        // 1.yield让出一次CPU使用权，但不释放锁，之后回到就绪队列
//        testYield();

        // 2.sleep程序休眠一段时间，但不释放锁，之后回到就绪队列
//        testSleep();

        // 3.join，让调用join的线程执行结束之后，当前线程才继续运行
        testJoin();
    }

    static void testYield() {
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("t1");
                    Thread.yield();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("t2");
                    Thread.yield();
                }
            }
        });
        t1.start();
        t2.start();
    }

    static void testSleep() {
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("t1");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("t2");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t1");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t2");
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();
    }
}
