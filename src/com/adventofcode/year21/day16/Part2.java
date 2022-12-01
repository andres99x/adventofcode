package com.adventofcode.year21.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Part2 {

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
        BigInteger answer = BigInteger.ZERO;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                StringBuilder sb = new StringBuilder();
                while (br.ready()) {
                    String line = br.readLine();
                    for (int i = 0; i < line.length(); i++) {
                        sb.append(hexToBin.get(line.charAt(i)));
                    }
                }

                String packets = sb.toString();
                answer = decodePackets(packets, null);
            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer.toString());
    }

    public static BigInteger decodePackets(String packets, BiFunction<BigInteger, BigInteger, BigInteger> operation) {
        Stack<BiFunction<BigInteger, BigInteger, BigInteger>> op = new Stack();
        Stack<Integer> nPackets = new Stack();
        Stack<BigInteger> resPackets = new Stack();
        BigInteger res = null;
        for (int i = 0; i < packets.length();) {
            if (packets.substring(i).chars().allMatch(c -> c == '0')) {
                break;
            }
            int type = Integer.parseInt(packets.substring(i + 3, i + 6), 2);
            i += 6;
            if (type == 4) {
                StringBuilder sb = new StringBuilder();
                String part;
                do {
                    part = packets.substring(i, i + 5);
                    sb.append(part.substring(1));
                    i += 5;
                } while (part.charAt(0) != '0');
                BigInteger val = new BigInteger(sb.toString(), 2);
                if (!nPackets.isEmpty()) {
                    resPackets.push(op.peek().apply(resPackets.pop(), val));
                    int n = nPackets.pop() - 1;
                    if (n > 0) {
                        nPackets.push(n);
                    } else {
                        op.pop();
                        val = resPackets.pop();
                    }
                }
                if (nPackets.isEmpty()) {
                    if (res == null) {
                        res = val;
                    } else {
                        res = operation.apply(res, val);
                    }
                }
            } else {
                if (operation == null) {
                    operation = getOperationForType(type);
                }
                int lLength = 15;
                boolean b = packets.substring(i, i + 1).equals("1");
                i += 1;
                if (b) {
                    lLength = 11;
                }
                int length = Integer.parseInt(packets.substring(i, i + lLength), 2);
                i += lLength;
                if (!b) {
                    BigInteger val = decodePackets(packets.substring(i, i + length), getOperationForType(type));
                    if (res == null) {
                        res = val;
                    } else {
                        res = operation.apply(res, val);
                    }
                    i += length;
                } else {
                    op.push(getOperationForType(type));
                    nPackets.push(length);
                }
            }

        }
        return res;
    }

    public static BiFunction<BigInteger, BigInteger, BigInteger> getOperationForType(int type) {
        switch (type) {
            case 0:
                return (a, b) -> a.add(b);
            case 1:
                return (a, b) -> a.multiply(b);
            case 2:
                return (a, b) -> a.min(b);
            case 3:
                return (a, b) -> a.max(b);
            case 5:
                return (a, b) -> a.compareTo(b) == 1 ? BigInteger.ONE : BigInteger.ZERO;
            case 6:
                return (a, b) -> a.compareTo(b) == -1 ? BigInteger.ONE : BigInteger.ZERO;
            case 7:
                return (a, b) -> a.compareTo(b) == 0 ? BigInteger.ONE : BigInteger.ZERO;
            default:
                return null;
        }
    }

}
