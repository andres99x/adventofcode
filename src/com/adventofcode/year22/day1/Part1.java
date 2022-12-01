package com.adventofcode.year22.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                int currentCalories = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    if  (line.equals("")) {
                        if (currentCalories > answer) {
                            answer = currentCalories;
                        }
                        currentCalories = 0;
                        continue;
                    }

                    currentCalories += Integer.parseInt(line);
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
