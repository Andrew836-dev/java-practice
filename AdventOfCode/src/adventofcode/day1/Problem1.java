/*
 * Day 1 Problems
 * Given a list of integers, find two numbers that add up to 2020, then print the product;
 * 
 * Part 2
 * Given the same list, find three numbers that add up to 2020, then print their product.
 */
package adventofcode.day1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem1 {

  List<Integer> expenseReport;

  public Problem1(List<Integer> expenseReport) {
    this.expenseReport = expenseReport;
  }

  public List<Integer> findSumOfTwoAndPrint(int targetSum) {
    List<Integer> output = new ArrayList<>();
    int startIndex = 1;
    int listSize = expenseReport.size();
    for (int i = 0; i < listSize; i++) {
      int firstExpense = expenseReport.get(i);
      for (int j = startIndex; j < listSize; j++) {
        int secondExpense = expenseReport.get(j);
        if (firstExpense + secondExpense == targetSum) {
          System.out.println(firstExpense + " * " + secondExpense + " = " + firstExpense * secondExpense);
          output.add(firstExpense);
          output.add(secondExpense);
        }
      }
      startIndex++;
    }
    return output;
  }

  public void findSumOfThreeAndPrint(int targetSum) {
    for (int thirdExpense : expenseReport) {
      int newTarget = targetSum - thirdExpense;
      List<Integer> firstTwo = findSumOfTwoAndPrint(newTarget);
      if (firstTwo.size() > 0) {
        int firstExpense = firstTwo.get(0);
        int secondExpense = firstTwo.get(1);
        int product = firstExpense * secondExpense * thirdExpense;
        System.out.println(firstExpense + " * " + secondExpense + " * " + thirdExpense + " = " + product);
        return;
//      return new int[]{firstExpense, secondExpense, thirdExpense};
      }
    }
//    return new int[]{};
  }
}
