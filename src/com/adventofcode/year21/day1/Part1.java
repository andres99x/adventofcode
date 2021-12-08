package com.adventofcode.year21.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1 {

    public static void main(String[] args) {
        short answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                int prevDepth = -1;
                while (br.ready()) {
                    int depth = Integer.parseInt(br.readLine());
                    if (prevDepth > 0 && depth > prevDepth) {
                        answer++;
                    }
                    prevDepth = depth;
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

}
