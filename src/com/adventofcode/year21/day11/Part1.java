package com.adventofcode.year21.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        int[][] matrix = new int[10][10];
        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int i = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    for (int j = 0; j < line.length(); j++) {
                        char c = line.charAt(j);
                        matrix[i][j] = Character.getNumericValue(c);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        boolean[][] flashed = null;
        for (int step = 0; step < 100; step++) {
            flashed = new boolean[10][10];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    answer += increaseEnergy(matrix, i, j, flashed);
                }
            }
        }
        
        System.out.println("Answer:" + answer);
    }

    static int increaseEnergy(int[][] matrix, int i, int j, boolean[][] flashed) {
        if (flashed[i][j]) {
            return 0;
        }
        matrix[i][j]++;

        if (matrix[i][j] > 9) {
            matrix[i][j] = 0;
            flashed[i][j] = true;

            int flashes = 1;
            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (0 <= k && k < matrix.length && 0 <= l && l < matrix[0].length) {
                        flashes += increaseEnergy(matrix, k, l, flashed);
                    }
                }
            }
            return flashes;
        }
        return 0;
    }

}
