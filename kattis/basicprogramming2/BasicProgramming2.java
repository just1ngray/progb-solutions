// https://open.kattis.com/problems/basicprogramming2
package kattis.basicprogramming2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class BasicProgramming2 {
    
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int arraySize = io.getInt();
        int taskCode = io.getInt();

        ArrayList<Integer> array = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i ++) {
            array.add(io.getInt());
        }

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

/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}