package me.bhradec.aoc2023.day1.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/day_1/input.txt"))) {
            Integer sum = lines
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
