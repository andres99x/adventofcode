package com.adventofcode.year21.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Part2 {

    public static void main(String[] args) {
        int answer = Integer.MAX_VALUE;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int maxH = 0;

                String[] rawPos = br.readLine().split(",");
                int[] crabPos = new int[rawPos.length];
                for (int i = 0; i < rawPos.length; i++) {
                    crabPos[i] = Integer.parseInt(rawPos[i]);
                    maxH = Math.max(maxH, crabPos[i]);
                }

                int[] fuel = new int[maxH + 1];
                for (int i = 0; i < crabPos.length; i++) {
                    int left = crabPos[i];
                    int right = crabPos[i];

                    int cost = 0;
                    while (left >= 0) {
                        cost += crabPos[i] - left;
                        fuel[left] += cost;
                        if (i == crabPos.length - 1) {
                            answer = Math.min(answer, fuel[left]);
                        }
                        left--;
                    }

                    cost = 0;
                    while (right <= maxH) {
                        cost += right - crabPos[i];
                        fuel[right] += cost;
                        if (i == crabPos.length - 1) {
                            answer = Math.min(answer, fuel[right]);
                        }
                        right++;
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
