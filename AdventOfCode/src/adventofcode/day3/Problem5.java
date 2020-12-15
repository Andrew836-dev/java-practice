/*
 * Day 3 Problems
 * Given a map of a hillside, that repeats infinitely to the right but stops at the bottom
 * a . marks an open space and a # marks a tree
 * starting from the top left and moving 3 squares right, 1 square down each tick
 * count how many trees are in those spaces
 *
 */
package adventofcode.day3;

import java.util.List;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem5 {

  final List<String> hillsideMap;

  public Problem5(List<String> input) {
    hillsideMap = input;
  }

  public int countTreesOnThePath(int rightMotion, int downMotion) {
    int limit = hillsideMap.size(), treeCounter = 0, xPosition = 0, rowLength;
    for (int yPosition = downMotion; yPosition < limit; yPosition += downMotion) {
      String row = hillsideMap.get(yPosition);
      rowLength = row.length();
      xPosition += rightMotion;
      if (xPosition >= rowLength) {
        xPosition -= rowLength;
      }
      System.out.println(yPosition + " " + row + " " + xPosition + " " + row.charAt(xPosition));
      if (row.charAt(xPosition) == '#') {
        treeCounter++;
      }
    }
    return treeCounter;
  }

}
