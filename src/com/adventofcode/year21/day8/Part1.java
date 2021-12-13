package com.adventofcode.year21.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        final String[] digits = new String[10];
        digits[0] = "abcefg";
        digits[1] = "cf";
        digits[2] = "acdeg";
        digits[3] = "acdfg";
        digits[4] = "bcdf";
        digits[5] = "abdfg";
        digits[6] = "abdefg";
        digits[7] = "acf";
        digits[8] = "abcdefg";
        digits[9] = "abcdfg";

        List<Integer>[] digitsBySegmentNumber = new List[7 + 1];
        for (int i = 0; i < digitsBySegmentNumber.length; i++) {
            digitsBySegmentNumber[i] = new ArrayList<>();
        }
        for (int i = 0; i < digits.length; i++) {
            digitsBySegmentNumber[digits[i].length()].add(i);
        }

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                while (br.ready()) {
                    String[] raw = br.readLine().split(" \\| ");
                    String[] outputDigits = raw[1].split(" ");
                    for (int i = 0; i < outputDigits.length; i++) {
                        if (digitsBySegmentNumber[outputDigits[i].length()].size() == 1) {
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
