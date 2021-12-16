package com.adventofcode.year21.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Part2 {

    static Map<Character, Character> map = Map.of('{', '}', '(', ')', '[', ']', '<', '>');
    static Map<Character, Integer> points = Map.of( '}', 3, ')', 1,']', 2, '>', 4);

    public static void main(String[] args) {
        long answer = 0;

        List<Long> linePoints = new ArrayList<>();
        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                Stack<Character> chunks = new Stack<>();
                while (br.ready()) {
                    String line = br.readLine();
                    chunks.clear();
                    long p = 0;
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (map.containsKey(c)) {
                            chunks.push(c);
                        } else if (chunks.isEmpty() || !map.get(chunks.pop()).equals(c)) {
                            chunks.clear();
                            break;
                        }
                    }
                    if (!chunks.isEmpty()) {
                        while (!chunks.isEmpty()) {
                            p *= 5;
                            p += points.get(map.get(chunks.pop()));
                        }
                        linePoints.add(p);
                    }
                }
                linePoints.sort(Comparator.naturalOrder());

                answer = linePoints.get(linePoints.size()/2);
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
