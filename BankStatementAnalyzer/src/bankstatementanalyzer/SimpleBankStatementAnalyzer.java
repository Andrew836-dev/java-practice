/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankstatementanalyzer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SimpleBankStatementAnalyzer {

    /**
     *
     * @param lines List of Strings from a CSV file, with the format
     * [Date],[Value],[Biller]
     */
    static public void printTransactionTotals(final List<String> lines) {
        double total = 0d;
        for (final String line : lines) {
            final double amount = getValue(line);
            total += amount;
        }
        System.out.println("The total for all transactions is " + total);
    }

    static public void printTransactionTotalsByMonth(final List<String> lines, final Month targetMonth) {
        double total = 0d;
        for (final String line : lines) {
            if (matchesTargetMonth(line, targetMonth)) {
                final double amount = getValue(line);
                total += amount;
            }
        }
        System.out.println("The total for all transactions in " + targetMonth + " is " + total);
    }

    static boolean matchesTargetMonth(final String line, final Month targetMonth) {
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        final String[] columns = line.split(",");
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        return date.getMonth() == targetMonth;
    }

    static double getValue(final String line) {
        final String[] columns = line.split(",");
        final double amount = Double.parseDouble(columns[1]);
        return amount;
    }

    static void printTopTenExpenses(final List<String> lines) {
        Comparator<String> expenseComparator = (String o1, String o2) -> {
            return (int) (getValue(o2) - getValue(o1));
        };
        System.out.println("Highest expenses in this statement :");
        lines.stream().sorted(expenseComparator).limit(10).forEachOrdered(System.out::println);
    }
}
