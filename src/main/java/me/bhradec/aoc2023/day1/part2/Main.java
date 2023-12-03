package me.bhradec.aoc2023.day1.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    private static final Map<String, Integer> wordMap = new HashMap<>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
    }};

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/day_1/input.txt"))) {
            Integer sum = lines
                    .map(line -> {
                        for (String word : wordMap.keySet()) {
                            // Keep the first and last character in case of overlap
                            line = line.replace(
                                    word,
                                    word.charAt(0) + wordMap.get(word).toString() + word.charAt(word.length() - 1)
                            );
                        }

                        return line;
                    })
                    .map(line -> line.replaceAll("[^0-9]", ""))
                    .map(line -> line.substring(0, 1) + line.substring(line.length() - 1))
                    .map(line -> line.length() > 1 ? Integer.parseInt(line) : Integer.parseInt(line) * 11)
                    .reduce(0, Integer::sum);

            System.out.println("The sum is: " + sum);
        } catch (IOException e) {
            System.err.println("Could not read input file");
        }
    }
}
