/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class FileReader {
  private static Stream<String> readFullFile(String targetFile) throws IOException {
    Path path = Paths.get(targetFile);
    return Files.lines(path);
  }
  
  private static Stream<String> readFileWithoutEmptyLines(String targetFile) throws IOException {
    return readFullFile(targetFile).filter(t -> !isEmptyLine(t));
  }
  
  private static boolean isEmptyLine(String line) {
    return "".equals(line.trim());
  }
  public static List<Integer> readAllAsInts(String targetFile) throws IOException {
    return readFileWithoutEmptyLines(targetFile)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
  }
  
  public static List<String> readAllAsStrings(String targetFile) throws IOException {
    return readFileWithoutEmptyLines(targetFile).collect(Collectors.toList());
  }
  
  public static List<String> readAllAsStringsIncludingEmpties(String targetFile) throws IOException {
    return readFullFile(targetFile).collect(Collectors.toList());
  }
}
