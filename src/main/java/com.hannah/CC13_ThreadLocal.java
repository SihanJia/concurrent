package com.hannah;

import java.util.concurrent.TimeUnit;

public class CC13_ThreadLocal {

    static ThreadLocal<Person> tl = new ThreadLocal<>();
    static class Person{
        String name;
        Person(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize");
        }
    }

    public static void main(String[] args) {
        new Thread(()-> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            tl.set(new Person("zhangsan"));
            tl.remove();
            System.gc();
        }).start();

//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//            System.gc();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
