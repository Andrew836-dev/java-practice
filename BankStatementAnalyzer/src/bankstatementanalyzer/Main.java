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

import java.io.IOException;
import java.nio.file.*;
import java.time.Month;
import java.util.*;

public class Main {

  public static final String RESOURCES = "src/main/resources";

  /**
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(final String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("Please enter a filename to analyze. ( e.g. /statement.csv )");
      return;
    }
    final BankStatementCSVParser parser = new BankStatementCSVParser();
    final String fileName = args[0];
    final Path path = Paths.get(RESOURCES + fileName);
    final List<String> lines = Files.readAllLines(path);
    final List<BankTransaction> bankTransactions = parser.parseLinesFromCSV(lines);

    System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
    System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
    System.out.println("Transactions in February " + selectInMonth(bankTransactions, Month.FEBRUARY));
    System.out.println("Transactions in March " + selectInMonth(bankTransactions, Month.MARCH));
  }

  private static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
    return bankTransactions.stream()
            .map((bankTransaction) -> bankTransaction.getAmount())
            .reduce(0d, (accumulator, _item) -> accumulator + _item);
  }

  private static double selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
    final List<BankTransaction> filteredList = bankTransactions.stream()
            .filter(transaction -> transaction.getDate().getMonth() == month)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    return calculateTotalAmount(filteredList);
  }

}
