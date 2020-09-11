// https://open.kattis.com/problems/pascal
package kattis.pascal;

import java.util.Scanner;

public class SlowPascal {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int counter = 0;
        for (int i = n-1; i >= 1; i --) {
            counter ++;
            if (n % i == 0) break;
        }

        System.out.println(counter);
    }

}
