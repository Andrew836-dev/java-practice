/*
 * The MIT License
 *
 * Copyright 2020 Andrew Gray <andrew.aj.gray@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
