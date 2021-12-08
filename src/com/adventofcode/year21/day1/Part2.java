package com.adventofcode.year21.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part2 {

    public static void main(String[] args) {
        short answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int wSize = 3;
                int wOffset = 1;
                int wTotalSize = wSize + wOffset;

                int[] window = new int[wTotalSize];
                int w1Sum = 0;
                int w2Sum = 0;

                // Fill window
                int wItems = 0;
                while (wItems < wTotalSize && br.ready()) {
                    int depth = Integer.parseInt(br.readLine());
                    window[wItems] = depth;

                    if (wItems < wSize) {
                        w1Sum += depth;
                    }
                    if (wItems >= wOffset) {
                        w2Sum += depth;
                    }
                    if (wItems == wTotalSize - 1 && w1Sum < w2Sum) {
                        answer++;
                    }
                    wItems++;
                }

                // Slide window till EOF
                while (br.ready()) {
                    int depth = Integer.parseInt(br.readLine());

                    // Slide window
                    for (int i = 0; i < wTotalSize; i++) {
                        if (i == 0) {
                            w1Sum -= window[i];
                        }
                        if (i == wOffset) {
                            w2Sum -= window[i];
                        }
                        if (i == wSize -1) {
                            w1Sum += window[i + 1];
                        }
                        if (i == wTotalSize - 1) {
                            w2Sum += depth;
                            window[i] = depth;
                            continue;
                        }
                        window[i] = window[i+1];
                    }

                    if (w1Sum < w2Sum) {
                        answer++;
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
