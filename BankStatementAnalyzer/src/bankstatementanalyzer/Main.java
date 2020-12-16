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
    final String fileName = args[0];
    final Path path = Paths.get(RESOURCES + fileName);
    final List<String> lines = Files.readAllLines(path);
    final BankStatementCSVParser parser = new BankStatementCSVParser();
    final List<BankTransaction> bankTransactions = parser.parseLinesFromCSV(lines);
    final BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

    System.out.println("The total for all transactions is " + processor.calculateTotalAmount());
    System.out.println("The total for transactions in January " + processor.calculateTotalInMonth(Month.JANUARY));
    System.out.println("The total for transactions in February " + processor.calculateTotalInMonth(Month.FEBRUARY));
    System.out.println("The total for transactions in March " + processor.calculateTotalInMonth(Month.MARCH));

    final String targetDescription = "Tesco";
    System.out.println("The total for transactions with description " + targetDescription + " " + processor.calculateTotalForDescription(targetDescription));
  }
}
