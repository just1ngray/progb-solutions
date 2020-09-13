// https://open.kattis.com/problems/sodaslurper
package kattis.sodaslurper;

import java.util.Scanner;

public class SodaSlurper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int emptyStart = sc.nextInt(); // < 1000: the number of empty soda bottles in Tim’s possession at the start of the day
        int emptyFoundOnStreet = sc.nextInt(); // < 1000: the number of empty soda bottles found during the day
        int emptyBottlesPerNew = sc.nextInt(); // 2 <= c < 2000: the number of empty bottles required to buy a new soda
        sc.close();

        int empties = emptyStart + emptyFoundOnStreet;
        int numDrank = 0;

        while (empties >= emptyBottlesPerNew) {
            int filled = empties / emptyBottlesPerNew;
            empties = empties % emptyBottlesPerNew + filled;
            numDrank += filled;
        }

        System.out.println(numDrank);
    }

}