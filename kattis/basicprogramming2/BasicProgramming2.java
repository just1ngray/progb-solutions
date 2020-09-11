// https://open.kattis.com/problems/basicprogramming2
package kattis.basicprogramming2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map;

public class BasicProgramming2 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int arraySize = sc.nextInt();
        int taskCode = sc.nextInt(); sc.nextLine();

        ArrayList<Integer> array = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i ++) {
            array.add(sc.nextInt());
        }
        sc.close();

        switch (taskCode) {
            case 1:
                addToTarget(array, 7777);
                break;
            case 2:
                isUnique(array);
                break;
            case 3:
                halfOccuring(array);
                break;
            case 4:
                medianIntegers(array);
                break;
            case 5:
                printRange(array, 100, 999);
                break;
            default:
                break;
        }
    }

    private static void addToTarget(ArrayList<Integer> array, int target) {
        int max = Collections.max(array);
        int min = target - max;

        Set<Integer> subset = array.stream()
            .filter(n -> n <= target && n >= min)
            .collect(Collectors.toSet());

        for (int n : subset) {
            for (int x : subset) {
                if (n != x && n + x == target) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    private static void isUnique(ArrayList<Integer> array) {
        HashSet<Integer> set = new HashSet<>(array);
        if (array.size() == set.size()) {
            System.out.println("Unique");
        } else System.out.println("Contains duplicate");
    }

    private static void halfOccuring(ArrayList<Integer> array) {
        int minOccurancesRequired = array.size() / 2;
        HashMap<Integer, Integer> occurances = new HashMap<>();
        
        for (int n : array) {
            if (occurances.containsKey(n)) {
                occurances.put(n, occurances.get(n) + 1);
            } else occurances.put(n, 1);
        }

        int maxOccurances = 0;
        int maxOccuringNumber = -1;
        for (Map.Entry<Integer, Integer> entry : occurances.entrySet()) {
            if (entry.getValue() > maxOccurances) {
                maxOccurances = entry.getValue();
                maxOccuringNumber = entry.getKey();
            }
        }

        if (maxOccurances > minOccurancesRequired) {
            System.out.println(maxOccuringNumber);
        } else {
            System.out.println("-1");
        }
    }

    private static void medianIntegers(ArrayList<Integer> array) {
        array.sort((a, b) -> a - b);
        int middle = (array.size() -1) / 2;

        if (array.size() % 2 == 0) System.out.println(
            array.get(middle) + " " + array.get(middle + 1));
        else
            System.out.println(array.get(middle));
    }

    private static void printRange(ArrayList<Integer> array, int min, int max) {
        StringBuilder sb = new StringBuilder();
        array.stream()
            .sorted()
            .filter(n -> n >= min && n <= max)
            .forEach(n -> sb.append(n + " "));
        System.out.println(sb);
    }

}
