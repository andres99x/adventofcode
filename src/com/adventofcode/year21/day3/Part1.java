package com.adventofcode.year21.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                short[] sum = new short[12];

                while (br.ready()) {
                    String line = br.readLine();
                    for (int i = 0; i < line.length(); i++) {
                        sum[i] += line.charAt(i) == '1' ? 1 : -1;
                    }
                }

                int gamma = 0;
                int epsilon = 0;
                for (int i = 0; i < sum.length; i++) {
                    if (sum[i] > 0) {
                        gamma |= (1 << sum.length - 1 - i);
                    } else {
                        epsilon |= (1 << sum.length - 1 - i);
                    }
                }

                answer = gamma * epsilon;
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
