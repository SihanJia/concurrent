package com.hannah;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

public class CC12_ProducerConsumer {

    ReentrantLock lock = new ReentrantLock();
    Condition producerCondition = lock.newCondition();
    Condition consumerCondition = lock.newCondition();

    LinkedList<String> list = new LinkedList<>();
    private int MAX = 10;
    private int count = 0;

    void put(String o) {
        lock.lock();
        while (list.size() == MAX) {
            try {
                producerCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        count++;
        System.out.println("put:" + o);
        consumerCondition.signalAll();
        lock.unlock();
    }

    String get() {
        lock.lock();
        while (list.size() == 0) {
            try {
                consumerCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String obj = list.removeFirst();
        count--;
        producerCondition.signalAll();
        lock.unlock();
        return obj;
    }

    synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        CC12_ProducerConsumer tt = new CC12_ProducerConsumer();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                while (true) {
                    tt.get();
                }
            }, "c" + i));
        }
        for (int i = 0; i < 2; i++) {
            threads.add(new Thread(() -> {
                while (true) {
                    tt.put(Thread.currentThread().getName() + " " + tt.count);
                }
            }, "p" + i));
        }
        threads.forEach(t -> t.start());
    }


}
