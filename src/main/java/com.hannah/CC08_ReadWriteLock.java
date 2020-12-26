package com.hannah;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CC08_ReadWriteLock {
//    static Lock lock = new ReentrantLock();

    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();
    static int value;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new Thread(() -> read(readLock)));
        }
        for (int i = 0; i < 2; i++) {
            threads.add(new Thread(() -> write(writeLock, new Random().nextInt())));
        }
        threads.forEach(t -> t.start());
    }

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(500);
            System.out.println("read");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(500);
            value = v;
            System.out.println("write");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
