package com.github.mmleczek.pi;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        int attPoints = 2000000000; // max value 2147483647
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        int pointsPerThread = attPoints / numberOfThreads;
        int remainingPoints = attPoints % numberOfThreads;

        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Points per thread: " + pointsPerThread);
        System.out.println("Remaining points : " + remainingPoints);

        CalcRunner[] calcRunners = new CalcRunner[numberOfThreads];
        Thread[] threadObjects = new Thread[numberOfThreads];

        long startTime = System.currentTimeMillis();

        for (int t = 0; t < numberOfThreads; t++) {
            int pointsToProcess = pointsPerThread + (t < remainingPoints ? 1 : 0);
            calcRunners[t] = new CalcRunner(pointsToProcess);
            threadObjects[t] = new Thread(calcRunners[t]);
            threadObjects[t].start();
        }

        int pointsInCircle = 0;
        for (int t = 0; t < numberOfThreads; t++) {
            try {
                threadObjects[t].join();
                pointsInCircle += calcRunners[t].getPointsInCircle();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        double pi = ((double) pointsInCircle * 4) / ((double) attPoints);
        System.out.println("Result: " + pi);
        System.out.println("Execution time: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}