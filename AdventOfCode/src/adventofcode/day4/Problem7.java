/*
 * Given a list of Strings, work out which passport data is represented
 * the passports span multiple lines, each passport is separated by an empty line
 * the data is all in the format <key>:<value>
 * passport requires keys = "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
 * optional key = "cid"
 * 
 * Part 2
 * validate the data associated with each key
 * byr, iyr and eyr must be 4 digits
 * byr must range between 1920 and 2002
 * iyr must range between 2010 and 2020
 * eyr must range between 2020 and 2030
 * hgt must be 150 to 193 if cm OR 59 to 76 if in
 * hcl must be a # followed by 6 alphanumeric characters
 * ecl must be one of "amb", "blu", "brn", "gry", "grn", "hzl", "oth"
 * pid must be a nine digit number including leading zeroes
 * cid is ignored
 */
package adventofcode.day4;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class Problem7 {

  List<Passport> dataList;

  private boolean hasAllKeysExceptCID(Passport holder) {
    Set<String> keys = holder.passport.keySet();
    String[] desiredKeys = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    for (String key : desiredKeys) {
      if (!keys.contains(key)) {
        return false;
      }
    }
    return true;
  }

  private class Passport {

    HashMap<String, String> passport;

    Passport(String input) {
      passport = new HashMap<>();
      addData(input);
    }

    final void addData(String input) {
      for (String data : input.split(" ")) {
        String key = data.split(":")[0];
        String value = data.split(":")[1];
        passport.put(key, value);
      }
    }

    boolean yearIsInRange(int startYear, int endYear, String targetField) {
      String field = passport.get(targetField);
      Pattern yearPattern = Pattern.compile("^[12][90][0-9][0-9]$");
      if (yearPattern.matcher(field).find()) {
        int value = Integer.parseInt(field);
        return value >= startYear && value <= endYear;
      }
      return false;
    }

    private boolean hasValidHeight() {
      String height = passport.get("hgt");
      if (height.endsWith("cm")) {
        int value = Integer.parseInt(height.replace("cm", ""));
        // between 150 and 193 for cm
        return value >= 150 && value <= 193;
      }
      if (height.endsWith("in")) {
        int value = Integer.parseInt(height.replace("in", ""));
        // between 59 and 76 for in
        return value >= 59 && value <= 76;
      }
      return false;
    }

    private boolean hasValidHairColor() {
      return Pattern.compile("#[0-9a-f]{6}").matcher(passport.get("hcl")).find();
    }

    private boolean hasValidEyeColor() {
      final String color = passport.get("ecl");
      String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
      return Arrays.stream(validColors).anyMatch(validColor -> color.equals(validColor));
    }

    private boolean hasValidPID() {
      String pid = passport.get("pid");
      Pattern idPattern = Pattern.compile("^[0-9]{9}$");
      return idPattern.matcher(pid).find();
    }

  }

  private boolean isEmptyLine(String input) {
    return "".equals(input.trim());
  }

  public Problem7(List<String> input) {
    dataList = new ArrayList<>();
    boolean needsNewPassport = true;
    for (String line : input) {
      if (needsNewPassport && !isEmptyLine(line)) {
        dataList.add(new Passport(line));
        needsNewPassport = false;
      } else if (!isEmptyLine(line)) {
        dataList.get(dataList.size() - 1).addData(line);
      } else {
        needsNewPassport = true;
      }
    }
  }

  private Stream<Passport> streamPassportsWithRequiredFields() {
    return dataList.stream()
            .filter(holder -> hasAllKeysExceptCID(holder));
  }

  public int countValidPassports() {
    return streamPassportsWithRequiredFields()
            .filter(passport -> passport.yearIsInRange(1920, 2002, "byr"))
            .filter(passport -> passport.yearIsInRange(2010, 2020, "iyr"))
            .filter(passport -> passport.yearIsInRange(2020, 2030, "eyr"))
            .filter(passport -> passport.hasValidHeight())
            .filter(passport -> passport.hasValidHairColor())
            .filter(passport -> passport.hasValidEyeColor())
            .filter(passport -> passport.hasValidPID())
            .map((_item_) -> 1)
            .reduce(0, Integer::sum);
  }

  public int countPassportsWithAllRequiredFields() {
    return streamPassportsWithRequiredFields()
            .map((_item_) -> 1)
            .reduce(0, Integer::sum);
  }

}
