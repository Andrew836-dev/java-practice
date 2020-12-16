/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import adventofcode.day5.Problem9;
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

    Problem9 problem = new Problem9(input);
    
    System.out.println(problem.findHighestSeatID());
    System.out.println(problem.findMissingSeatID());
  }

}
