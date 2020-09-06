// https://open.kattis.com/problems/notamused
package kattis.notamused;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class NotAmused {

    public static double DOLLARS_PER_MIN = 0.10;

    public NotAmused() {
        HashMap<Integer, ArrayList<AmusementCustomer>> days = new HashMap<>();

        // read input
        Scanner sc = new Scanner(System.in);
        int dayNum = 1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;

            String[] components = line.split(" ");

            if (line.equals("OPEN")) {
                days.put(dayNum, new ArrayList<AmusementCustomer>());
            } else if (line.equals("CLOSE")) {
                dayNum ++;
            } else {
                String customerName = components[1];

                AmusementCustomer customer = null;
                for (AmusementCustomer ac : days.get(dayNum)) {
                    if (ac.name.equals(customerName)) {
                        customer = ac;
                        break;
                    }
                }

                if (customer == null) {
                    customer = new AmusementCustomer(customerName, line);
                    days.get(dayNum).add(customer);
                } else {
                    customer.addLog(line);
                }
            }
        }
        sc.close();

        // format output
        for (int i = 1; i < dayNum; i ++) {
            if (i != 1) System.out.println();

            ArrayList<AmusementCustomer> customers = days.get(i);
            customers.sort((a, b) -> a.name.compareTo(b.name));

            System.out.println("Day " + i);
            customers.forEach(c -> System.out.println(c));
        }
    }

    public static void main(String[] args) {
        new NotAmused();
    }

    private class AmusementCustomer {
        private ArrayList<String> logs;
        private String name;

        public AmusementCustomer(String name, String firstLog) {
            this.name = name;
            logs = new ArrayList<>();
            logs.add(firstLog);
        }

        public void addLog(String log) {
            logs.add(log);
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("#0.00");
            return name + " $" + df.format(getMinsInPark() * DOLLARS_PER_MIN);
        }

        private int getMinsInPark() {
            int sum = 0;
            int enterTime = 0;

            for (String log : logs) {
                String[] components = log.split(" ");
                
                if (log.startsWith("ENTER")) {
                    enterTime = Integer.parseInt(components[2]);
                } else if (log.startsWith("EXIT")) {
                    sum += Integer.parseInt(components[2]) - enterTime;
                    enterTime = 0;
                }
            }

            return sum;
        }

    }

}