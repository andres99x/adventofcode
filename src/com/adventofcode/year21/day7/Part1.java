package com.adventofcode.year21.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        int answer = Integer.MAX_VALUE;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
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

                    while (left >= 0) {
                        fuel[left] += crabPos[i] - left;
                        if (i == crabPos.length - 1) {
                            answer = Math.min(answer, fuel[left]);
                        }
                        left--;
                    }

                    while (right <= maxH) {
                        fuel[right] += right - crabPos[i];
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
