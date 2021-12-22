package com.adventofcode.year21.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {

    static int DEPTH = 10;

    public static void main(String[] args) {
        long answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                Map<String, Long> splitPolymer = new HashMap<>();
                Map<String, Character> rules = new HashMap<>();
                Map<Character, Long> elements = new HashMap<>();
                while (br.ready()) {
                    String line = br.readLine();
                    if (splitPolymer.isEmpty()) {
                        for (int i = 0; i < line.length() - 1; i++) {
                            splitPolymer.put(line.substring(i, i + 2), splitPolymer.getOrDefault(line.substring(i, i + 2), 0L) + 1);
                            if (i == 0) {
                                elements.put(line.charAt(0), elements.getOrDefault(line.charAt(0), 0L) + 1);
                            }
                            elements.put(line.charAt(i + 1), elements.getOrDefault(line.charAt(i + 1), 0L) + 1);
                        }
                    } else if (!line.isBlank()) {
                        String[] pair = line.split(" -> ");
                        rules.put(pair[0], pair[1].charAt(0));
                    }
                }


                for (int i = 0; i < DEPTH; i++) {
                    Map<String, Long> newSplitPolymer = new HashMap<>();
                    for (String pair : splitPolymer.keySet()) {
                        if (rules.containsKey(pair)) {
                            Character el = rules.get(pair);
                            long newElements = splitPolymer.get(pair);
                            elements.put(el, elements.getOrDefault(el, 0L) + newElements);
                            String p0 = pair.charAt(0) + "" + el;
                            String p1 = el + "" + pair.charAt(1);
                            newSplitPolymer.put(p0, newSplitPolymer.getOrDefault(p0, 0L) + newElements);
                            newSplitPolymer.put(p1, newSplitPolymer.getOrDefault(p1, 0L) + newElements);
                        }
                    }
                    splitPolymer = newSplitPolymer;
                }


                long max = Long.MIN_VALUE;
                long min = Long.MAX_VALUE;
                for (Character el : elements.keySet()) {
                    max = Math.max(max, elements.get(el));
                    min = Math.min(min, elements.get(el));
                }
                answer = max - min;
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
