package com.adventofcode.year22.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                int[] topCalories = new int[3];
                int currentCalories = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    if  (line.equals("")) {

                        for (int i = 0; i < topCalories.length; i++) {
                            if (currentCalories > topCalories[i]) {
                                int aux = topCalories[i];
                                topCalories[i] = currentCalories;
                                currentCalories = aux;
                            }
                        }

                        currentCalories = 0;
                        continue;
                    }
                    currentCalories += Integer.parseInt(line);
                }
                answer = Arrays.stream(topCalories).sum();
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
