package com.adventofcode.year21.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int x = 0;
                int y = 0;

                while (br.ready()) {
                    String[] line = br.readLine().split(" ");

                    Command command = Command.valueOf(line[0]);
                    int units = Integer.parseInt(line[1]);
                    switch (command) {
                        case up:
                            y -= units;
                            break;
                        case down:
                            y += units;
                            break;
                        case forward:
                            x += units;
                            break;
                        default:
                            break;
                    }
                }

                answer = x * y;
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }
}
