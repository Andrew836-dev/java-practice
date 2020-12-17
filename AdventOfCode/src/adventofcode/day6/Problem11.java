/*
 * Given a list of Strings, where the data is split into groups separated by empty lines
 * each line has lower case alphabet characters on it
 * 
 * count the sum of unique letters in each group
 * 
 * Part 2
 * 
 * count the sum of each letter that occurs in every line of a group
 */
package adventofcode.day6;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem11 {

  Set<String> alphabet;
  List<String> groupList;

  public Problem11(List<String> input) {
    alphabet = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).collect(Collectors.toSet());
    groupList = new ArrayList<>();
    String accumulator = "";
    for (String line : input) {
      if (!isEmpty(line)) {
        accumulator += line.trim() + "\n";
      } else {
        groupList.add(accumulator.trim());
        accumulator = "";
      }
    }
  }

  private boolean isEmpty(String line) {
    return "".equals(line.trim());
  }

  private Set<String> getUniqueLetters(String line) {
    if (line.length() == 0) {
      return Collections.emptySet();
    }
    return new HashSet(Arrays.asList(line.split("")));
  }

  private void addLettersToMap(HashMap<String, Integer> groupMap, String line) {
    for (String letter : getUniqueLetters(line)) {
      if (groupMap.containsKey(letter)) {
        groupMap.put(letter, groupMap.get(letter) + 1);
      } else {
        groupMap.put(letter, 1);
      }
    }
  }

  private int countSameLettersInAllLines(String group) {
    String[] lines = group.split("\n");
    HashMap<String, Integer> groupMap = new HashMap<>();
    for (String line : lines) {
      addLettersToMap(groupMap, line);
    }
    return groupMap.values().stream()
            .mapToInt(value -> value == lines.length ? 1 : 0)
            .sum();
  }

  public int sumOfLettersEntireGroupSelected() {
    return groupList.stream()
            .mapToInt(this::countSameLettersInAllLines)
            .sum();
  }

  public int sumQuestionsPerGroup() {
    return groupList.stream()
            .mapToInt(String::length)
            .sum();
  }

  public int sumUniqueQuestionsPerGroup() {
    return groupList.stream()
            .map((group) -> group.replace("\n", ""))
            .map(this::getUniqueLetters)
            .mapToInt(Set::size)
            .sum();
  }
}
