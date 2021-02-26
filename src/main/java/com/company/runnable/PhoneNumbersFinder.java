package com.company.runnable;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class PhoneNumbersFinder implements Runnable {
    private final Path pathFrom;
    private final File fileTo;

    public PhoneNumbersFinder(File fileTo) {
        pathFrom = (new File("text.txt").toPath());
        this.fileTo = fileTo;
    }

    @Override
    public void run() {
        String s = find();
        synchronized (fileTo) {
            try {
                Files.write((fileTo).toPath(),
                        ("Found phone numbers at " + pathFrom + ": " + s + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String find() {
        StringBuilder sum = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(pathFrom);

            for (String line : lines) {

                Pattern pattern = Pattern.compile("(\\+380)[\\d]{9}");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {

                    sum.append(matcher.group()).append(" ");

                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return sum.toString();
    }


}
