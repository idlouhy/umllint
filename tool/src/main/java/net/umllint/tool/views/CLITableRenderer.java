package net.umllint.tool.views;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */

public class CLITableRenderer {

    protected PrintStream out;
    protected String separator = " ";

    public CLITableRenderer() {
        out = System.out;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String render(List<String> header, List<List<String>> table) {

        StringBuilder out = new StringBuilder();
        Map<Integer, Integer> widths = new HashMap<Integer, Integer>();

        //compute max column width
        Integer index = 0;
        for (String cell : header) {
            widths.put(index, cell.length());
            index++;
        }

        for (List<String> row : table) {

            index = 0;
            for (String cell : row) {
                Integer length = Math.max(widths.get(index), cell.length());
                widths.put(index, length);
                index++;
            }
        }

        index = 0;
        for (String cell : header) {
            out.append(String.format(" %1$-" + widths.get(index) + "s ", cell));
            out.append(separator);
            index++;
        }
        out.append(String.format("%n"));

        for (List<String> row : table) {

            index = 0;
            for (String cell : row) {
                out.append(String.format(" %1$-" + widths.get(index) + "s ", cell));
                out.append(separator);
                index++;
            }
            out.append(String.format("%n"));
        }

        return out.toString();
    }
}





