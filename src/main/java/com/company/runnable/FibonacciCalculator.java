package com.company.runnable;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@RequiredArgsConstructor
public class FibonacciCalculator implements Runnable {
    private final int amountOfNumbers;
    private final File fileTo;

    @Override
    public void run() {
        long number = calculate();
        synchronized (fileTo){
        try {
            Files.write(fileTo.toPath(),
                    ("fibonacci number #"+amountOfNumbers+": "+number+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }}
    }


    /**
     * В этом Метогде используется полная формула Бине
     */

    private long calculate() {
        double sqrtOfFive = Math.sqrt(5.00);
        double phi = (1 + sqrtOfFive) / 2;
        return (long) ((Math.pow(phi, amountOfNumbers) - Math.pow(-phi, -amountOfNumbers)) / sqrtOfFive);

    }
}
