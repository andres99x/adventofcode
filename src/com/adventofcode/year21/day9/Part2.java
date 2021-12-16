package com.adventofcode.year21.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {


    public static void main(String[] args) {
        int answer = 0;

        List<List<Integer>> heatmap = new ArrayList<>();
        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                while (br.ready()) {
                    String line = br.readLine();
                    List<Integer> tmp = new ArrayList<>();
                    for (int i = 0; i < line.length(); i++) {
                        tmp.add(Character.getNumericValue(line.charAt(i)));
                    }
                    heatmap.add(tmp);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        int[] largestBasins = new int[3];
        for (int i = 0; i < heatmap.size(); i++) {
            for (int j = 0; j < heatmap.get(0).size(); j++) {
                List<Integer[]> basin = new ArrayList<>();
                calculateBasin(heatmap, heatmap.get(i).get(j), i, j, basin);

                for (int k = 0; k < largestBasins.length; k++) {
                    if (k == 0) {
                        if (basin.size() > largestBasins[k]) {
                            largestBasins[k] = basin.size();
                            continue;
                        }
                    } else if (largestBasins[k - 1] > largestBasins[k]) {
                        int tmp = largestBasins[k];
                        largestBasins[k] = largestBasins[k - 1];
                        largestBasins[k - 1] = tmp;
                        continue;
                    }
                    break;
                }
            }
        }
        answer = largestBasins[0] * largestBasins[1] * largestBasins[2];
        System.out.println("Answer:" + answer);
    }

    static void calculateBasin(List<List<Integer>> heatmap, int val, int x, int y, List<Integer[]> basin) {
        if (x >= 0 && y >= 0 & x < heatmap.size() && y < heatmap.get(0).size()
                && !basin.stream().anyMatch(p -> Arrays.equals(p, new Integer[] { x, y }))
                && heatmap.get(x).get(y) < 9
                && (basin.isEmpty() || heatmap.get(x).get(y) > val)) {
            basin.add(new Integer[] { x, y });

            calculateBasin(heatmap, heatmap.get(x).get(y), x - 1, y, basin);
            calculateBasin(heatmap, heatmap.get(x).get(y), x, y - 1, basin);
            calculateBasin(heatmap, heatmap.get(x).get(y), x + 1, y, basin);
            calculateBasin(heatmap, heatmap.get(x).get(y), x, y + 1, basin);
        }
    }

}
