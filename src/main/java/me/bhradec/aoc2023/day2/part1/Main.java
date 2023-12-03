package me.bhradec.aoc2023.day2.part1;

import me.bhradec.aoc2023.day2.shared.CubeColor;
import me.bhradec.aoc2023.day2.shared.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Main {
    public static final Integer AVAILABLE_RED_CUBES = 12;
    public static final Integer AVAILABLE_GREEN_CUBES = 13;
    public static final Integer AVAILABLE_BLUE_CUBES = 14;

    private static Integer findMaxByColor(CubeColor color, List<String> cubes) {
        String colorName = switch (color) {
            case RED -> "red";
            case GREEN -> "green";
            case BLUE -> "blue";
        };

        return cubes
                .stream()
                .filter(cube -> cube.contains(colorName))
                .map(cube -> cube.replaceAll("[^0-9]", ""))
                .map(Integer::parseInt)
                .reduce(Integer::max)
                .orElseThrow(NoSuchElementException::new);
    }

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/day_2/input.txt"))) {
            Long sum = lines
                    .map(line -> {
                        String gameName = line.split(":")[0];
                        String gameSets = line.split(":")[1];

                        List<String> cubes = Stream.of(gameSets.split(";"))
                                .flatMap(set -> Stream.of(set.split(",")))
                                .toList();

                        Long gameId = Long.parseLong(gameName.replaceAll("[^0-9]", ""));

                        return new Game(
                                gameId,
                                findMaxByColor(CubeColor.RED, cubes),
                                findMaxByColor(CubeColor.GREEN, cubes),
                                findMaxByColor(CubeColor.BLUE, cubes)
                        );
                    })
                    .filter(game -> game.maxRedCount() <= AVAILABLE_RED_CUBES
                            && game.maxGreenCount() <= AVAILABLE_GREEN_CUBES
                            && game.maxBlueCount() <= AVAILABLE_BLUE_CUBES)
                    .map(game -> game.id())
                    .reduce(0L, Long::sum);

            System.out.println("The sum is: " + sum);
        } catch (IOException e) {
            System.err.println("Could not read input file");
        }
    }
}
