/*
 * Given a list of strings, use the information to find a row, column and seat ID
 * The strings are 10 characters long, the first 7 give the information on which row
 * the last 3 characters have the information on which column
 * there are 128 rows, numbered from 0 to 127
 *  - an B means the row is in the higher half of the range
 *  - a F means the row is in the lower half of the range
 * there are 8 columns, numbered from 0 to 7
 *  - an R means the column is in the higher half of the range
 *  - an L means the column is in the lower half of the range
 * the seat ID is found by (row * 8) + column
 * 
 * what is the highest seat ID in the input?
 *
 * Part 2
 * find the missing seat ID in the dataset. 
 * It's guaranteed to not be at the beginning or end.
 * It's guaranteed to be a single gap in the ordered list.
 */
package adventofcode.day5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem9 {

  Stream<Seat> seatStream;

  public Problem9(List<String> input) {
    seatStream = input.stream()
            .map(Seat::new);
  }

  private class Seat {

    final String pass;
    int column;
    int row;
    int ID;

    public Seat(String pass) {
      column = 0;
      row = 0;
      this.pass = pass;

      for (int i = 0; i < 10; i++) {
        if (pass.charAt(i) == 'B') {
          row += 128 / ((int) Math.pow(2, i + 1));
        }
        if (pass.charAt(i) == 'R') {
          column += 8 / (Math.pow(2, (i - 6)));
        }
      }
      ID = (row * 8) + column;
    }

    @Override
    public String toString() {
      return "Seat{ID: " + ID + " Column: " + column + " Row: " + row + " Pass: " + pass + "}";
    }

  }

  private Stream<Seat> sortedSeatList() {
    return seatStream.sorted((left, right) -> right.ID - left.ID);
  }
  
  private Stream<Integer> sortedSeatID() {
    return sortedSeatList().map(seat -> seat.ID);
  }

  public int findHighestSeatID() {
    return sortedSeatID()
            .limit(1)
            .reduce(0, Integer::sum);
  }

  public int findMissingSeatID() {
    List<Integer> sortedSeatIDs = sortedSeatID().collect(Collectors.toList());
    int first = sortedSeatIDs.get(0);
    for (int i = 1; i < sortedSeatIDs.size(); i++) {
      int second = sortedSeatIDs.get(i);
      if ((first - second) > 1) {
        return first - 1;
      }
      first = second;
    }
    // fail state
    return -1;
  }

}
