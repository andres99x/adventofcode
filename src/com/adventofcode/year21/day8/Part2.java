package com.adventofcode.year21.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

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
        int[][] sharedSegments = new int[10][10];
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (i == j) {
                    if (digitsBySegmentNumber[digits[i].length()] == null) {
                        digitsBySegmentNumber[digits[i].length()] = new ArrayList<>();
                    }
                    digitsBySegmentNumber[digits[i].length()].add(i);

                    sharedSegments[i][j] = digits[i].length();
                    continue;
                }
                if (i > j) {
                    sharedSegments[i][j] = sharedSegments[j][i];
                    continue;
                }
                for (int k = 0; k < digits[i].length(); k++) {
                    if (digits[j].indexOf(digits[i].charAt(k)) >= 0) {
                        sharedSegments[i][j]++;
                        if (sharedSegments[i][j] == digits[j].length()) {
                            break;
                        }
                    }
                }
            }
        }

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                while (br.ready()) {
                    String[] raw = br.readLine().split(" \\| ");

                    // Order digits for easier mapping.
                    String[] newDigits = Arrays.stream(raw[0].split(" "))
                            .map(d -> d.toCharArray())
                            .map(d -> {
                                Arrays.sort(d);
                                return d;
                            })
                            .map(String::new)
                            .toArray(String[]::new);

                    // Order digits for easier mapping.
                    String[] outputDigits = Arrays.stream(raw[1].split(" "))
                            .map(d -> d.toCharArray())
                            .map(d -> {
                                Arrays.sort(d);
                                return d;
                            })
                            .map(String::new)
                            .toArray(String[]::new);

                    Map<String, Integer> known = new HashMap<>();
                    Map<String, List<Integer>> unknown = new HashMap<>();
                    // Recognize unique digits by segments count
                    for (int i = 0; i < newDigits.length; i++) {
                        if (digitsBySegmentNumber[newDigits[i].length()].size() == 1) {
                            known.put(newDigits[i], digitsBySegmentNumber[newDigits[i].length()].get(0));
                        } else {
                            unknown.put(newDigits[i], new ArrayList<>(digitsBySegmentNumber[newDigits[i].length()]));
                        }
                    }

                    while (unknown.size() > 0) {
                        String found = null;
                        outer:
                        for (String s : unknown.keySet()) {
                            for (String k : known.keySet()) {
                                int commonSegments = 0;
                                // Calculate shared segments between the unknown and known digits.
                                for (int i = 0; i < k.length(); i++) {
                                    if (s.indexOf(k.charAt(i)) >= 0) {
                                        commonSegments++;
                                    }
                                }

                                // Compare shared segments and discard candidates if not match
                                for (int i = 0; i < unknown.get(s).size(); i++) {
                                    if (sharedSegments[known.get(k)][unknown.get(s).get(i)] != commonSegments) {
                                        unknown.get(s).remove(unknown.get(s).get(i));
                                    }
                                    // When we have a new known digit break
                                    if (unknown.get(s).size() == 1) {
                                        found = s;
                                        break outer;
                                    }
                                }
                            }
                        }
                        // Remove the found digit from unknown and add it to the know list.
                        if (found != null) {
                            known.put(found, unknown.remove(found).get(0));
                        }
                    }

                    int output = 0;
                    for (int i = outputDigits.length -1; i >= 0; i--) {
                        output += known.get(outputDigits[i]) * Math.pow(10, outputDigits.length - 1 - i);
                    }
                    answer += output;

                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
