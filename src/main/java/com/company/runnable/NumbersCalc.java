package com.company.runnable;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class NumbersCalc implements Runnable {
    private final int amountOfNumbers;
    private final File fileTo;

    @Override
    public void run() {
        long sum = calculate();
        synchronized (fileTo) {
            try {
                Files.write(fileTo.toPath(),
                        ("Sum of " + amountOfNumbers + " numbers: " + sum + "\n").getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long calculate() {
        int i = 0;
        long sum = 0;
        try {
            List<String> lines = Files.readAllLines((new File("numbers.txt")).toPath());

            for (String line : lines) {

                Pattern pattern = Pattern.compile("^\\d+$");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find() && i < amountOfNumbers) {

                    sum += Long.parseLong(line);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
