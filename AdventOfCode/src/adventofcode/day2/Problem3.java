/*
 * Day 2 Problems
 * Given a list of Strings, use the information to count how many passwords are valid.
 * The strings are in the format "<int>-<int> <char>: <String>"
 * the string at the end is the password
 * the char is the required letter
 * the first int is the minimum number of times the letter must appear
 * the second int is the maximum number of times the letter can appear
 * 
 * A valid password will have between min and max occurences of the required letter
 *
 * Part 2
 * Using the same list of requirements, count how many are valid with the new interpretation
 * the minimum number is now the first possible location for the required letter
 * the maximum number is now the second possible location for the required letter
 * (One-indexed)
 *
 * A valid password will have the required letter at only one of the possible locations
 *
 */
package adventofcode.day2;

import java.util.List;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem3 {
  List<String> passwordList;
  public Problem3(List<String> input) {
    passwordList = input;
  }
  
  class PasswordRequirement {
    final int min;
    final int max;
    final String password;
    final char targetLetter;

    PasswordRequirement(String input) {
      String[] components = input.split(" ");
      min = Integer.parseInt(components[0].split("-")[0]);
      max = Integer.parseInt(components[0].split("-")[1]);
      targetLetter = components[1].charAt(0);
      password = components[2];
    }
    
  }

  public int countValidPasswordsType1() {
    int counter = 0;
    for (String requirements : passwordList) {
      PasswordRequirement req = new PasswordRequirement(requirements);
      int letterCount = 0;
      for (char letter : req.password.toCharArray()) {
        if (letter == req.targetLetter) {
          letterCount++;
        }
      }
      if (letterCount >= req.min && letterCount <= req.max) {
        counter++;
      }
    }
    return counter;
  }
  
  private boolean has_one_or_both_locations_correct(PasswordRequirement requirement) {
    return (requirement.password.charAt(requirement.min - 1) == requirement.targetLetter) 
            || (requirement.password.charAt(requirement.max - 1) == requirement.targetLetter)
            ;
  }
  
  private boolean has_only_one_character_in_place(PasswordRequirement requirement) {
    return !(
            (requirement.password.charAt(requirement.max - 1) == requirement.targetLetter) 
            && (requirement.password.charAt(requirement.min - 1) == requirement.targetLetter)
            );
  }

  public int countValidPasswordsType2() {
    return passwordList.stream()
            .map((requirement) -> new PasswordRequirement(requirement))
            .filter((requirement) -> has_one_or_both_locations_correct(requirement))
            .filter((requirement) -> has_only_one_character_in_place(requirement))
            .map((_item_)-> 1)
            .reduce(0, Integer::sum);
  }
  
}
