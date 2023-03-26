package org.example;

import java.util.Date;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        run(false);
        System.out.println("-".repeat(80));
        run(true);
    }

    private static void run(boolean vThreads) {

        System.out.println("Using vThreads: " + vThreads);

        long start = System.currentTimeMillis();
        System.out.println("   Start: " + new Date());

        Random random = new Random();
        Runnable runnable = () -> {
            double i = random.nextDouble(1000) % random.nextDouble(1000);
        };

        for (int i = 0; i < 100000; i++) {
            if (vThreads) {
                Thread.startVirtualThread(runnable);
            } else {
                Thread t = new Thread(runnable);
                t.start();
            }
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Run time: " + timeElapsed + " ms.");
        System.out.println("     End: " + new Date());
    }
}