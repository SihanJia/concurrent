package com.hannah;

public class CC11_Test {
    public static void main(String[] args) {

        Object o = new Object();
        new Thread(() -> {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                System.out.print(a);
                synchronized (o) {
                    a++;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() ->
        {
            int a = 1;
            for (int i = 0; i < 26; i++) {
                System.out.print(a);
                synchronized (o) {
                    a++;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
