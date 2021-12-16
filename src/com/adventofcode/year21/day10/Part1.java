package com.adventofcode.year21.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Part1 {

    static Map<Character, Character> map = Map.of('{', '}', '(', ')', '[', ']', '<', '>');
    static Map<Character, Integer> points = Map.of( '}', 1197, ')', 3,']', 57, '>', 25137);

    public static void main(String[] args) {
        int answer = 0;

        Stack<Character> chunks = new Stack<>();
        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                while (br.ready()) {
                    String line = br.readLine();
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (map.containsKey(c)) {
                            chunks.push(c);
                        } else if (chunks.isEmpty() || !map.get(chunks.pop()).equals(c)) {
                            answer += points.get(c);
                            break;
                        }
                    }
                    chunks.empty();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }
        
        System.out.println("Answer:" + answer);
    }

}
