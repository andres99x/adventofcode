package com.adventofcode.year21.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        int answer = 0;

        try (InputStream is = Part2.class.getResourceAsStream("Input.txt")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int[] numbers = null;

                List<int[][]> boards = new ArrayList<>();
                int[][] tmpBoard = new int[5][5];

                int nRows = 0;
                while (br.ready()) {
                    String line = br.readLine();
                    String[] rawNumbers = null;

                    if (numbers == null) {
                        rawNumbers = line.split(",");
                        numbers = new int[rawNumbers.length];
                        for (int i = 0; i < rawNumbers.length; i++) {
                            numbers[i] = Integer.parseInt(rawNumbers[i]);
                        }
                        br.readLine();
                        continue;
                    }

                    if (line.isEmpty()) {
                        boards.add(tmpBoard);
                        tmpBoard = new int[5][5];
                        nRows = 0;
                        continue;
                    }

                    rawNumbers = line.trim().replaceAll("  ", " ").split(" ");
                    for (int i = 0; i < rawNumbers.length; i++) {
                        tmpBoard[nRows][i] = Integer.parseInt(rawNumbers[i]);
                    }
                    nRows++;
                }
                boards.add(tmpBoard);

                boolean[] winnerBoards = new boolean[boards.size()];
                outer:
                for (int i = 0; i < numbers.length; i++) {
                    for (int x = 0; x < boards.size(); x++) {
                        if (winnerBoards[x]) {
                            continue;
                        }
                        int res = checkBoard(boards.get(x), numbers[i]);
                        if (res > 0) {
                            answer = res;
                            winnerBoards[x] = true;
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading Input.txt");
            e.printStackTrace();
        }

        System.out.println("Answer:" + answer);
    }

    public static int checkBoard(int[][] board, int num) {
        int markedNumbers = 0;
        int sumUnmarked = 0;

        boolean bingo = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == num) {
                    board[i][j] = ~board[i][j];
                }
                if (board[i][j] < 0) {
                    markedNumbers++;
                } else {
                    sumUnmarked += board[i][j];
                }

                // At end of horizontal line
                if (!bingo && markedNumbers >= 5 && j == board[i].length - 1) {
                    boolean winnerLine = true;
                    for (int k = j; winnerLine && k >= 0; k--) {
                        winnerLine = board[i][k] < 0;
                    }
                    bingo = winnerLine;
                }

                // At end of vertical line
                if (!bingo && markedNumbers >= 5 && i == board.length - 1) {
                    boolean winnerLine = true;
                    for (int k = i; winnerLine && k >= 0; k--) {
                        winnerLine = board[k][j] < 0;
                    }
                    bingo = winnerLine;
                }

                if (bingo) {
                    int a = 0;
                }
            }
        }

        return bingo ? sumUnmarked * num : -1;
    }

}
