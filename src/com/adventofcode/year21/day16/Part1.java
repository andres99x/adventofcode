package com.adventofcode.year21.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Part1 {

    static Map<Character, String> hexToBin = new HashMap<>() {{
        put('0', "0000");
        put('1', "0001");
        put('2', "0010");
        put('3', "0011");
        put('4', "0100");
        put('5', "0101");
        put('6', "0110");
        put('7', "0111");
        put('8', "1000");
        put('9', "1001");
        put('A', "1010");
        put('B', "1011");
        put('C', "1100");
        put('D', "1101");
        put('E', "1110");
        put('F', "1111");
    }};

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part1.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                StringBuilder sb = new StringBuilder();
                while (br.ready()) {
                    String line = br.readLine();
                    for (int i = 0; i < line.length(); i++) {
                        sb.append(hexToBin.get(line.charAt(i)));
                    }
                }

                String packets = sb.toString();
                answer = sumPacketsVersions(packets);
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

    public static int sumPacketsVersions(String packets) {
        int sum = 0;
        for (int i = 0; i < packets.length();) {
            if (packets.substring(i).chars().allMatch(c -> c == '0')) {
                break;
            }
            sum += Integer.parseInt(packets.substring(i, i + 3), 2);
            i += 3;
            int type = Integer.parseInt(packets.substring(i, i + 3), 2);
            i += 3;
            if (type == 4) {
                while (packets.substring(i, i + 5).charAt(0) != '0') {
                    i += 5;
                }
                i += 5;
            } else {
                int lLength = 15;
                boolean b = packets.substring(i, i + 1).equals("1");
                i += 1;
                if (b) {
                    lLength = 11;
                }
                int length = Integer.parseInt(packets.substring(i, i + lLength), 2);
                i += lLength;
                if (!b) {
                    sum += sumPacketsVersions(packets.substring(i, i + length));
                    i += length;
                }
            }
        }
        return sum;
    }

}
