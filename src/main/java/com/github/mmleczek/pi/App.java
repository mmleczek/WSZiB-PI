package com.github.mmleczek.pi;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        int attPoints = 2000000000;
        int pointsInCircle = 0;
        Random random = new Random();
        for(int i = 0; i < attPoints; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double distanceFromCenter = Math.sqrt(x * x + y * y);
            if (distanceFromCenter < 1) {
                pointsInCircle++;
            }
        }
        double pi = ((double)pointsInCircle * 4) / ((double)attPoints);
        System.out.println(pi);
    }
}