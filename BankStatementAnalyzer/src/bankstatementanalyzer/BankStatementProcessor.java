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

import java.time.Month;
import java.util.*;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class BankStatementProcessor {

  private final List<BankTransaction> bankTransactions;

  public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }

  public double calculateTotalAmount() {
    return reduceListIntoTotalValue(bankTransactions);
  }

  public double calculateTotalForDescription(String description) {
    final List<BankTransaction> filteredList = filterTransactionsByDescription(description);
    return reduceListIntoTotalValue(filteredList);
  }

  public double calculateTotalInMonth(final Month month) {
    final List<BankTransaction> filteredList = filterTransactionsByMonth(month);
    return reduceListIntoTotalValue(filteredList);
  }

  private List<BankTransaction> filterTransactionsByDescription(final String description) {
    final List<BankTransaction> filteredList = bankTransactions.stream()
            .filter(transaction -> description.equals(transaction.getDescription()))
            .collect(toList());
    return filteredList;
  }

  private List<BankTransaction> filterTransactionsByMonth(final Month month) {
    final List<BankTransaction> filteredList = bankTransactions.stream()
            .filter(transaction -> transaction.getDate().getMonth() == month)
            .collect(toList());
    return filteredList;
  }

  private double reduceListIntoTotalValue(final List<BankTransaction> transactionList) {
    return transactionList.stream()
            .map((bankTransaction) -> bankTransaction.getAmount())
            .reduce(0d, (accumulator, _item) -> accumulator + _item);
  }

}
