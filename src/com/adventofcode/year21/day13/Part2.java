package com.adventofcode.year21.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
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

                boolean[][] paper = new boolean[max + 1][max + 1];
                while (!dots.isEmpty()) {
                    int[] dot = dots.remove(0);
                    paper[dot[0]][dot[1]] = true;
                }


                int maxX = max + 1;
                int maxY = max + 1;
                while (br.ready()) {
                    String[] fold = br.readLine().split("fold along ")[1].split("=");
                    String axis = fold[0];
                    int pos = Integer.parseInt(fold[1]);

                    for (int i = 0; i < pos; i++) {
                        for (int j = 0; j < max; j++) {
                            int x = axis.equals("x") ? i : j;
                            int y = axis.equals("x") ? j : i;

                            int xMirror = axis.equals("x") ? pos - i + pos : j;
                            int yMirror = axis.equals("x") ? j : pos - i + pos;

                            paper[x][y] |= paper[xMirror][yMirror];
                        }
                    }

                    maxX = axis.equals("x") ? pos : maxX;
                    maxY = axis.equals("x") ? maxY : pos;
                }

                for (int i = 0; i < maxY; i++) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = 0; j < maxX; j++) {
                        if (paper[j][i]) {
                            sb.append("#");
                        } else {
                            sb.append(".");
                        }
                    }
                    System.out.println(sb);
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }
    }

}
