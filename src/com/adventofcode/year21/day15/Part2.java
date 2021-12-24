package com.adventofcode.year21.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Part2 {

    static int tileWidth = 100;
    static int tileHeight = 100;

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int[][] cave = new int[5 * tileWidth][5 * tileHeight];

                int row = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    for (int i = 0; i < line.length(); i++) {
                        cave[row][i] = Integer.parseInt(line.substring(i, i + 1));
                        for (int j = 0; j  < 5 ; j++) {
                            for (int k = 0; k < 5; k++) {
                                if (j == 0 && k == 0) {
                                    continue;
                                }
                                int risk = (cave[row][i] + j + k) / 10 + (cave[row][i] + j + k) % 10;
                                cave[row + j * tileWidth][i + k * tileHeight] = risk;
                            }
                        }
                    }
                    row++;
                }

                int[][] risk = new int[cave.length][cave[0].length];
                for (int i = 0; i < risk.length; i++) {
                    for (int j = 0; j < risk[0].length; j++) {
                        if (i == 0 && j == 0 ) {
                            continue;
                        }
                        risk[i][j] = Integer.MAX_VALUE;
                    }
                }

                PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(pos -> pos[2]));
                pq.add(new Integer[] { 0, 0 ,0 });

                while (!pq.isEmpty()) {
                    Integer[] curr = pq.poll();

                    // Risk has been updated and Entry is old.
                    if (curr[2] != risk[curr[0]][curr[1]]) {
                        continue;
                    }

                    for (int i = -1; i <= 1 ; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (Math.abs(i) == Math.abs(j)) {
                                continue;
                            }

                            int nextX = curr[0] + i;
                            int nextY = curr[1] + j;
                            if (nextX < 0 || nextX >= cave.length || nextY < 0 || nextY >= cave[0].length) {
                                continue;
                            }

                            if (risk[nextX][nextY] > curr[2] + cave[nextX][nextY]) {
                                risk[nextX][nextY] = curr[2] + cave[nextX][nextY];
                                pq.add(new Integer[] { nextX, nextY, risk[nextX][nextY] });
                            }
                        }
                    }

                }

                answer = risk[cave.length - 1][cave[0].length - 1];
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
