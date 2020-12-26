package com.hannah;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CC10_Test {

    static Container container = new Container();

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        Object o = new Object();

        new Thread(() -> {
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("已经添加5个元素");
                o.notify();
            }
//            while (true) {
//                if (container.size() == 5) {
//                    System.out.println("已经添加5个元素");
//                    break;
//                }
//            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    container.add("item " + i);
                    System.out.println("item" + i);
                    if (container.size() == 5) {
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                latch.countDown();
                }
            }
        }).start();
    }

    static class Container {
        List<Object> list = new ArrayList<>();

        synchronized void add(Object o) {
            list.add(o);
        }

        synchronized int size() {
            return list.size();
        }
    }
}
