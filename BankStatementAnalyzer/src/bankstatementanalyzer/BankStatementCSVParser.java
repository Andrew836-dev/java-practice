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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankStatementCSVParser {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  private BankTransaction parseFromCSV(final String line) {
    final String[] columns = line.split(",");
    final double amount = Double.parseDouble(columns[1]);
    final String category = columns[2];
    final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
    return new BankTransaction(amount, category, date);
  }
  
  public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
    List<BankTransaction> bankTransactions = new ArrayList<>();
    for (String line : lines) {
      bankTransactions.add(parseFromCSV(line));
    }
    return bankTransactions;
  }
}
