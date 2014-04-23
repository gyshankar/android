package com.example1.test4;

public class App {

  public static final int SIZE = 9;
  public static void main(String[] args) {
    int[][] matrix = new int[SIZE][SIZE];
    matrix[0][0] = 5;
    matrix[0][1] = 3;
    matrix[1][4] = 8;
    matrix[3][5] = 9;
    matrix[8][8] = 1;
    System.out.println("Before:");
    print(matrix);
    System.out.println("After:");
    if (solve(matrix, 0, 0)) {
      print(matrix);
    }
  }

  private static void print(int[][] matrix) {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println("");
    }
  }

  private static boolean solve(int[][] matrix, int row, int col) {
    //System.out.println(row + "," + col);
    if (col == SIZE) {
      col = 0;
      row +=1;
      if (row == 9) {
        return true;
      }
    }
    if (matrix[row][col] != 0) {
      return solve(matrix, row, col+1);
    }
    for (int num=1; num <= SIZE; num++) {
      if (legal(matrix, row, col, num)) {
        matrix[row][col] = num;
        if (solve(matrix, row, col+1)) {
          return true;
        }
      }
    }
    matrix[row][col] = 0;
    return false;
  }

  private static boolean legal(int[][] matrix, int row, int col, int num) {
    for (int i=0;i< SIZE; i++){
      if (matrix[row][i] == num) {
        return false;
      }
    }
    for (int i=0;i< SIZE; i++){
      if (matrix[i][col] == num) {
        return false;
      }
    }
    return true;
  }
}
