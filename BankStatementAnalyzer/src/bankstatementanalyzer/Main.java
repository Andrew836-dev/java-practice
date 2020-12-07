/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankstatementanalyzer;

import java.io.IOException;
import java.nio.file.*;
import java.time.Month;
import java.util.List;

/**
 *
 * @author Npsyc
 */
public class Main {

    public static final String RESOURCES = "src/main/resources";

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(final String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please enter a filename to analyze.");
            return;
        }
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);
        SimpleBankStatementAnalyzer.printTransactionTotals(lines);
        SimpleBankStatementAnalyzer.printTransactionTotalsByMonth(lines, Month.JANUARY);
        SimpleBankStatementAnalyzer.printTransactionTotalsByMonth(lines, Month.FEBRUARY);
        SimpleBankStatementAnalyzer.printTransactionTotalsByMonth(lines, Month.MARCH);
        SimpleBankStatementAnalyzer.printTopTenExpenses(lines);
    }

}
