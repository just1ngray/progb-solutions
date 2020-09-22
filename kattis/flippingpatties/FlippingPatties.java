// https://open.kattis.com/problems/flippingpatties
package kattis.flippingpatties;

import java.util.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class FlippingPatties {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        Map<Integer, Integer> actions = new HashMap<>(); // second, #actions

        int numOrders = io.getInt(); //Number of orders
        for (int i = 0; i < numOrders; i ++) {
            // for a burger b, customer wants it cooked for t, and delivered on d
            int t = io.getInt(); // to cook one side
            int d = io.getInt(); // time to serve (deliver)

            // start cooking at d-2t
            // flipped at       d-t
            // taken off at     d
            int[] actionTimes = { d-2*t, d-t, d };
            for (int actionIndex = 0; actionIndex < 3; actionIndex ++) {
                int time = actionTimes[actionIndex];

                if(!actions.containsKey(time)) actions.put(time, 1);
                else actions.put(time, actions.get(time) + 1);
            }
        }

        // Max(actions per second) / 2 = answer
        int maxActions = Collections.max(actions.values());
        int numChefs = (int) Math.ceil(maxActions / 2.0);
        io.println(numChefs);

        io.close();
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
