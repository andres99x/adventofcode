package com.adventofcode.year21.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                short sum = 0;
                List<String> ones = new ArrayList<>();
                List<String> zeros = new ArrayList<>();

                while (br.ready()) {
                    String line = br.readLine();
                    if (line.charAt(0) == '1') {
                        ones.add(line);
                        sum += 1;
                    } else {
                        zeros.add(line);
                        sum += -1;
                    }
                }

                String o2 = reduce(sum >= 0 ? ones : zeros, true, 1);
                String co2 = reduce(sum >= 0 ? zeros : ones, false, 1);

                answer = Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

    public static String reduce(List<String> l, boolean high, int pos) {
        if (l.size() == 1) {
            return l.get(0);
        }

        int mode = 0;
        List<String> ones = new ArrayList();
        List<String> zeros = new ArrayList();

        for (String s : l) {
            if (s.charAt(pos) == '1') {
                mode += 1;
                ones.add(s);
            } else {
                mode += -1;
                zeros.add(s);
            }
        }

        return reduce(high ? (mode >= 0 ? ones : zeros) : (mode >= 0 ? zeros : ones), high,pos + 1);
    }

}
