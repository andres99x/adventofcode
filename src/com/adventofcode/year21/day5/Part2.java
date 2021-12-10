package com.adventofcode.year21.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int maxX = 0;
                int maxY = 0;

                List<int[]> lines = new ArrayList<>();
                while (br.ready()) {
                    String[] rawLine = br.readLine().split(" -> ");
                    String[] p1 = rawLine[0].split(",");
                    String[] p2 = rawLine[1].split(",");

                    int[] line = new int[] { Integer.parseInt(p1[0]), Integer.parseInt(p1[1]),
                            Integer.parseInt(p2[0]), Integer.parseInt(p2[1]) };

                    maxX = Math.max(maxX, Math.max(line[0],  line[2]));
                    maxY = Math.max(maxY, Math.max(line[1],  line[3]));
                    lines.add(line);
                }
                int[][] matrix = new int[maxY+1][maxX+1];

                for (int[] line : lines) {
                    int movX = line[2] - line[0];
                    int sigX = Integer.signum(movX);

                    int movY = line[3] - line[1];
                    int sigY = Integer.signum(movY);

                    int absMov = Math.max(Math.abs(movX), Math.abs(movY));

                    for (int i = 0; i <= absMov; i++) {
                        int x = line[0] + (sigX * i);
                        int y = line[1] + (sigY * i);
                        if (++matrix[y][x] == 2) {
                            answer++;
                        }
                    }
                }


            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
