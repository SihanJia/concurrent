package com.hannah;

import java.util.concurrent.Phaser;

public class CC07_Phaser {

    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("到齐" + registeredParties);
                    return false;
                case 1:
                    System.out.println("吃饭" + registeredParties);
                    return false;
                case 2:
                    System.out.println("拥抱" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrived() {
            System.out.println(name + "到达");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            System.out.println(name + "吃饭");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                System.out.println("拥抱");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrived();
            eat();
            hug();
        }
    }


}
