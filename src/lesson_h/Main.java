package lesson_h;

import java.util.Arrays;

public class Main {

    public static int[][] method(int a, int b){
        int[][] array = new int[a][b];
        int count = 1;
        for (int i = 0; i < b; i++) {
            array[0][i] = count;
            count++;
        }
        for (int i = 1; i < a; i++) {
            array[i][b - 1] = count;
            count++;
        }
        for (int i = b - 2; i >= 0; i--) {
            array[a - 1][i] = count;
            count++;
        }
        for (int i = a - 2; i > 0; i--) {
            array[i][0] = count;
            count++;
        }
        int c = 1;
        int d = 1;
        while (count < a * b) {
            while (array[c][d + 1] == 0) {
                array[c][d] = count;
                count++;
                d++;
            }
            while (array[c + 1][d] == 0) {
                array[c][d] = count;
                count++;
                c++;
            }
            while (array[c][d - 1] == 0) {
                array[c][d] = count;
                count++;
                d--;
            }
            while (array[c - 1][d] == 0) {
                array[c][d] = count;
                count++;
                c--;
            }
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (array[i][j] == 0) {
                    array[i][j] = count;
                }
            }
        }
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }
        return array;
    }

    public static void main(String[] args) {
        method(7,5);
    }
}
