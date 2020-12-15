/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import adventofcode.day1.Problem1;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class AdventExecutor {

  /**
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException{
    List<Integer> input = FileReader.readAsInts("src/adventofcode/input.txt");

    Problem1 problem = new Problem1(input);

    problem.findSumOfTwoAndPrint(2020);
    problem.findSumOfThreeAndPrint(2020);
  }

}
