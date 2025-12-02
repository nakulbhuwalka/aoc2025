package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws IOException {
        List<Data> list = loadData("Data2.txt");
        //List<Data> list = loadData("Test2.txt");

        challenge2(list);

    }

    private static void challenge2(List<Data> list) {
        long total = 0;
        for (Data data : list) {
            for (long i = data.start; i <= data.end; i++) {
                if(!isValid2(i))
                {
                    total+=i;
                }
            }

        }
        System.out.println(total);
    }

    private static void challenge1(List<Data> list) {
        long total = 0;
        for (Data data : list) {
            for (long i = data.start; i <= data.end; i++) {
                if(!isValid(i))
                {
                    total+=i;
                }
            }

        }
        System.out.println(total);
    }

    static boolean isValid(long number)
    {
        String asString = String.valueOf(number);
        int length = asString.length();
        if (length% 2 !=0)
        {
            return true;
        }
        return !asString.substring(length / 2).equals(asString.substring(0, length / 2));
    }

    static boolean isValid2(long number)
    {
        String asString = String.valueOf(number);
        int length = asString.length();

        for (int size = 1; size <= length / 2; size++) {
            if(length % size == 0)
            {
                int segments = length / size;
                Set<String> set = new HashSet<>();
                for (int segment = 0; segment < segments; segment++) {
                    set.add(asString.substring(segment*size, segment*size + size));
                }
                if(set.size() ==1)
                {
                    return false;
                }

            }
        }
        return true;


    }


    private static List<Data> loadData(String file) throws IOException {
        String contents = Files.readString(Paths.get("src/main/resources/", file));
        return Arrays.stream(contents.split(","))
                .map(range -> range.split("-"))
                .map(range -> new Data(Long.parseLong(range[0]), Long.parseLong(range[1])))
                .toList();
    }

    record Data(long start, long end) {}


}
