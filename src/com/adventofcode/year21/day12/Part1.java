package com.adventofcode.year21.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

public class Part1 {

    static String START = "start";
    static String END = "end";


    public static void main(String[] args) {
        int answer = 0;
        Map<String, List<String>> paths = new HashMap<>();

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                while (br.ready()) {
                    String[] path = br.readLine().split("-");
                    if (!paths.containsKey(path[0])) {
                        paths.put(path[0], new ArrayList<>());
                    }
                    if (!paths.containsKey(path[1])) {
                        paths.put(path[1], new ArrayList<>());

                    }
                    paths.get(path[0]).add(path[1]);
                    paths.get(path[1]).add(path[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        Stack<String> visited = new Stack<>();
        answer = explore(paths, START, visited);

        System.out.println("Answer:" + answer);
    }

    static int explore(Map<String, List<String>> paths, String cave, Stack<String> visited) {
        if (cave.equals(END)) {
            return 1;
        }
        if (cave.equals(cave.toLowerCase(Locale.ROOT)) && visited.search(cave) != -1) {
            return 0;
        }

        int i = 0;
        visited.push(cave);
        for (String c : paths.get(cave)) {
            i += explore(paths, c, visited);
        }
        visited.pop();

        return i;
    }

}
