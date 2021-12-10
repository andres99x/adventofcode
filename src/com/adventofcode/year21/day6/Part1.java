package com.adventofcode.year21.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                final int days = 80;

                int[] timer = Arrays.stream(br.readLine().split(","))
                        .map(n -> Integer.parseInt(n))
                        .collect(() -> new int[9], (acc, n) -> acc[n]++, (acc1, acc2) -> {
                            for (int i = 0; i < acc1.length; i++) {
                                acc1[i] += acc2[1];
                            }
                        });


                for (int i = 0; i < days; i++) {
                    int c = 0;
                    for (int j = 8; j >= 0; j--) {
                        if (j == 0) {
                            timer[6] += timer[8];
                            continue;
                        }
                        c = timer[j -1];
                        timer[j -1] = timer[8];
                        timer[8] = c;
                    }
                }

                for (int i = 0; i < timer.length; i++) {
                    answer += timer[i];
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
