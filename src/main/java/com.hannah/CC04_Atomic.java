package com.hannah;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CC04_Atomic {

    private AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0;i<1000;i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        CC04_Atomic t = new CC04_Atomic();
        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++) {
            threads.add(new Thread(t::m, "Thread-" + i));
        }
        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
