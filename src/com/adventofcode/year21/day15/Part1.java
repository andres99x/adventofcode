package com.adventofcode.year21.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                List<List<Integer>> cave = new ArrayList<>();
                int row = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    cave.add(new ArrayList<>());
                    for (int i = 0; i < line.length(); i++) {
                        cave.get(row).add(Integer.parseInt(line.substring(i, i + 1)));
                    }
                    row++;
                }

                int[][] risk = new int[cave.size()][cave.get(0).size()];
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
                            // Discard diagonal moves.
                            if (Math.abs(i) == Math.abs(j)) {
                                continue;
                            }

                            int nextX = curr[0] + i;
                            int nextY = curr[1] + j;
                            // Check it's inside the cave.
                            if (nextX < 0 || nextX >= cave.size() || nextY < 0 || nextY >= cave.get(0).size()) {
                                continue;
                            }

                            // If new risk is lower, update and queue.
                            if (risk[nextX][nextY] > curr[2] + cave.get(nextX).get(nextY)) {
                                risk[nextX][nextY] = curr[2] + cave.get(nextX).get(nextY);
                                pq.add(new Integer[] { nextX, nextY, risk[nextX][nextY] });
                            }
                        }
                    }

                }

                answer = risk[cave.size() - 1][cave.get(0).size() - 1];

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
