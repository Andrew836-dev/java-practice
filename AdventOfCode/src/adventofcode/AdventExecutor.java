/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import adventofcode.day3.Problem5;
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
    List<String> input = FileReader.readAllAsStrings("src/adventofcode/input.txt");

    Problem5 problem = new Problem5(input);

    System.out.println(problem.countTreesOnThePath(3,1));
    int oneOne = problem.countTreesOnThePath(1,1);
    int threeOne = problem.countTreesOnThePath(3, 1);
    int fiveOne = problem.countTreesOnThePath(5, 1);
    int sevenOne = problem.countTreesOnThePath(7, 1);
    int oneTwo = problem.countTreesOnThePath(1, 2);
    System.out.println(oneOne + " " + threeOne + " " + fiveOne + " " + sevenOne + " " + oneTwo);
    System.out.println(1l * oneOne * threeOne * fiveOne * sevenOne * oneTwo);
  }

}
