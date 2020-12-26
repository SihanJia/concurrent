package com.hannah;

import java.util.concurrent.Exchanger;

public class CC10_Exchanger {
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger();
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "thread1").start();
        new Thread(() -> {
            String s = "T2";
//            try {
////                s = exchanger.exchange(s);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "thread2").start();
    }
}
