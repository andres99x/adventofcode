package com.adventofcode.year21.day13;

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

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int max = 0;
                List<int[]> dots = new ArrayList<>();
                while (br.ready()) {
                    String line = br.readLine();
                    if (line.isBlank()) {
                        break;
                    }
                    String[] dot = line.split(",");
                    int x = Integer.parseInt(dot[0]);
                    int y = Integer.parseInt(dot[1]);
                    dots.add(new int[] {x, y});
                    max = Math.max(max, Math.max(x, y));
                }
                answer = dots.size();

                boolean[][] paper = new boolean[max + 1][max + 1];
                while (!dots.isEmpty()) {
                    int[] dot = dots.remove(0);
                    paper[dot[0]][dot[1]] = true;
                }

                String[] fold = br.readLine().split("fold along ")[1].split("=");
                String axis = fold[0];
                int pos = Integer.parseInt(fold[1]);
                for (int i = 0; i < pos; i++) {
                    for (int j = 0; j < max; j++) {
                        if (axis.equals("x")) {
                            if (paper[pos - i + pos][j] && paper[i][j]) {
                                answer--;
                            }
                            paper[i][j] |= paper[pos - i + pos][j];
                        } else {
                            if (paper[j][pos - i + pos] && paper[j][i]) {
                                answer--;
                            }
                            paper[j][i] |= paper[j][pos - i + pos];
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
