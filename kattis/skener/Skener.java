// https://open.kattis.com/problems/skener
package kattis.skener;

import java.util.Scanner;

public class Skener {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nRows = sc.nextInt();
        int nColumns = sc.nextInt();
        int zoomR = sc.nextInt();
        int zoomC = sc.nextInt(); sc.nextLine();
        char[][] input = new char[nRows][nColumns];

        for (int r = 0; r < nRows; r ++) {
            char[] rowCharArray = sc.nextLine().toCharArray();
            input[r] = rowCharArray;
        }
        
        sc.close();

        for (int r = 0; r < nRows * zoomR; r ++) {
            for (int c = 0; c < nColumns * zoomC; c ++) {

                System.out.print(input[r / zoomR][c / zoomC]);

            }
            System.out.println();
        }
    }
    
}

// rows columns zoom(rows) zoom(columns)