package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {
    public static void main(String[] args) throws IOException {


        DialResult result = Files.lines(Paths.get("src/main/resources/Test1.txt"))
                .reduce(new DialResult(50, 0), Day1::move1, (a, b) -> b);
        System.out.println(result);


        result = Files.lines(Paths.get("src/main/resources/Test1.txt"))
                .reduce(new DialResult(50, 0), Day1::move2, (a, b) -> b);
        System.out.println(result);

        result = Files.lines(Paths.get("src/main/resources/Data1.txt"))
                .reduce(new DialResult(50, 0), Day1::move2, (a, b) -> b);
        System.out.println(result);
    }


    static DialResult move2(DialResult start, String command) {
        char direction = command.charAt(0);
        int count = Integer.parseInt(command.substring(1));
        if (direction == 'L') {
            count = -count;
        }
        int newCount;
        int clicks;

        newCount = start.start + count;

        if (newCount <= 0) {
            clicks = (Math.abs(newCount) / 100);
            if (start.start > 0) {
                clicks++;
            }
        } else {
            clicks = newCount / 100;
        }
        newCount = (newCount + 100 * (clicks + 1)) % 100;

        return new DialResult(newCount, start.total + clicks);
    }

    static DialResult move1(DialResult start, String command) {
        char direction = command.charAt(0);
        int count = Integer.parseInt(command.substring(1));
        int newCount;
        if (direction == 'R') {
            newCount = (start.start + count) % 100;
        } else {
            newCount = (start.start - count + 100) % 100;
        }
        if (newCount == 0) {
            return new DialResult(newCount, start.total + 1);
        } else {
            return new DialResult(newCount, start.total);
        }
    }

    record DialResult(int start, int total) {
    }

    ;

}
