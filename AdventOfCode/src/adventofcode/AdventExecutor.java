/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import adventofcode.day2.Problem3;
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

    Problem3 problem = new Problem3(input);

    System.out.println(problem.countValidPasswordsType1());
    System.out.println(problem.countValidPasswordsType2());
  }

}
