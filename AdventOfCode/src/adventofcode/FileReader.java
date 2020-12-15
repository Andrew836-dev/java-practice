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

/**
 *
 * @author Andrew Gray <andrew.aj.gray@gmail.com>
 */
public class FileReader {
  public static List<Integer> readAsInts(String targetFile) throws IOException {
    Path path = Paths.get(targetFile);
    return Files.readAllLines(path).stream().map(Integer::parseInt).collect(Collectors.toList());
  }
}
