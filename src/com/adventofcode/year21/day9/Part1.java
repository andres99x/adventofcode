package com.adventofcode.year21.day9;

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

        List<Integer[]> heatmap = new ArrayList<>();
        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                while (br.ready()) {
                    String line = br.readLine();
                    Integer[] tmp = new Integer[line.length()];
                    for (int i = 0; i < line.length(); i++) {
                        tmp[i] = Character.getNumericValue(line.charAt(i));
                    }
                    heatmap.add(tmp);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        for (int i = 0; i < heatmap.size(); i++) {
            for (int j = 0; j < heatmap.get(0).length; j++) {
                boolean low = true;
                next:
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (0 <= k && k < heatmap.size() && 0 <= l && l < heatmap.get(0).length) {
                            if (i == k && j != l || i != k && j == l) {
                                low = heatmap.get(i)[j] < heatmap.get(k)[l];
                                if (!low) {
                                    break next;
                                }
                            }
                        }
                    }
                }
                if (low) {
                    answer += heatmap.get(i)[j] + 1;
                }
            }
        }
        
        System.out.println("Answer:" + answer);
    }

}
