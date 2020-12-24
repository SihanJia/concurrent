package com.hannah;

public class CC01_CreateThread {

    public static void main(String[] args) {
        // 1.继承Thread
        new MyThread().start();
        // 2.实现Runnable
        new Thread(new MyRun()).start();
        // 实现runnable接口也可以用lambda表达式
        new Thread(() -> {
            System.out.println("lambda runnable");
        }).start();

        // 3.线程池
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("My thread");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("My run");
        }
    }
}
