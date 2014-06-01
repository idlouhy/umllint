package net.umllint.tool.views;

import java.io.PrintStream;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class CLIExportCSVRenderer {

    protected PrintStream out;

    public CLIExportCSVRenderer() {
        out = System.out;
    }

    public String render(List<String> header, List<List<String>> table) {

        for (String cell : header) {
            out.append(String.format("%s;", cell));
        }
        out.append(String.format("%n"));

        for (List<String> row : table) {
            for (String cell : row) {
                out.append(String.format("%s;", cell));
            }
            out.append(String.format("%n"));
        }

        return out.toString();
    }
}





