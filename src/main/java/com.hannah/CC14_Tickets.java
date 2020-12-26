package com.hannah;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CC14_Tickets {

    public static void main(String[] args) {
//        ListTickets();
        QueueTickets();
    }

    static void ListTickets() {
        Object o = new Object();
        LinkedList<String> tickets = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket" + i);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (o) {
                    while (tickets.size() > 0) {
                        String ticket = tickets.removeFirst();
                        System.out.println(Thread.currentThread().getName() + " 卖了 " + ticket);
                        o.notifyAll();
                        try {
                            o.wait();
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "Thread" + i).start();
        }
    }

    static void QueueTickets() {
        Queue<String> tickets = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket" + i);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String ticket = tickets.poll();
                    if (ticket == null) break;
                    System.out.println(Thread.currentThread().getName() + " 卖了 " + ticket);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }

            }, "Thread" + i).start();
        }
    }
}
