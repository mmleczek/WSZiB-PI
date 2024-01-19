package com.github.mmleczek.pi;

import java.util.Random;

public class CalcRunner implements Runnable {
    private final int pointsToProcess;
    private int pointsInCircle;

    public CalcRunner(int pointsToProcess) {
        this.pointsToProcess = pointsToProcess;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < pointsToProcess; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double distanceFromCenter = Math.sqrt(x * x + y * y);

            if (distanceFromCenter < 1) {
                pointsInCircle++;
            }
        }
    }

    public int getPointsInCircle() {
        return pointsInCircle;
    }
}
