package com.hannah;

public class CC00_ThreadState {

    static Thread t1 = new T1();
    static Thread t2 = new T2();

    public static void main(String[] args) {
        new Thread(new MyRun()).start();
        new Thread(() ->
                System.out.println("Hello Myrun.")
        );
        t1.start();
//        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.run();
    }

    private static class MyRun implements Runnable {
        public void run() {
            System.out.println("Hello Myrun.");
        }
    }

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("t1");
//                try {
//                    t2.notify();
//                    t1.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    private static class T2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("t2");
//                try {
//                    t1.notify();
//                    t2.wait();
//                } catch (InterruptedException e) {
//                    System.out.println("Interrupted Exception: " + Thread.currentThread().getState());
//                }
            }
        }
    }
}
