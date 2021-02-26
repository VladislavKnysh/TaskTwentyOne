package com.company;

import com.company.runnable.FibonacciCalculator;
import com.company.runnable.NumbersCalc;
import com.company.runnable.PhoneNumbersFinder;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("out.txt");
        Thread th1 = new Thread(new FibonacciCalculator(15, file));
        Thread th2 = new Thread(new NumbersCalc(10, file));
        Thread th3 = new Thread(new PhoneNumbersFinder(file));
        th1.start();
        th2.start();
        th3.start();
        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
