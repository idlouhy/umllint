package net.umllint.tool.views;

import net.umllint.tool.model.result.ULResult;
import net.umllint.tool.model.result.ULResultBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class CLIResultsRenderer {

  List<String> header = new LinkedList<String>();
  List<List<String>> table = new LinkedList<List<String>>();

  CLITableRenderer tableRenderer = new CLITableRenderer();
  CLIExportCSVRenderer exportCSVTableRenderer = new CLIExportCSVRenderer();

  private void render(List<ULResult> patterns) {

    header.clear();
    table.clear();

    header.add("ID");
    header.add("Name");
    //header.add("Severity");
    header.add("Binding");
    header.add("Path");

    for (ULResult pattern : patterns) {

      for (ULResultBinding binding : pattern.getBindings()) {

        List<String> row = new LinkedList<String>();

        row.add(pattern.getId());
        row.add(pattern.getName());
        //row.add(binding.getElement().getId());
        row.add(binding.getName());

        //render element pointer
        StringBuilder s = new StringBuilder();
        //s.append(UMLEntityDirectory.getInstance().resolveXmiId(binding.getElement().getId()));
        s.append(binding.getName());

        ULResultBinding current = binding;
        while (current.getBinding() != null) {
          s.append(" > ");
          //s.append(UMLEntityDirectory.getInstance().resolveXmiId(current.getElement().getId()));
          s.append(current.getBinding().getName());
          current = current.getBinding();
        }

        row.add(s.toString());
        table.add(row);

        //render only first binding
        break;
      }
    }
  }

  public String renderTable(List<ULResult> patterns) {
    render(patterns);
    tableRenderer.setSeparator(" ");
    return tableRenderer.render(header, table);
  }

  public String renderExportCSV(List<ULResult> patterns) {
    render(patterns);
    return exportCSVTableRenderer.render(header, table);
  }

  public static void main(String[] args) throws Exception {

      /*
        ULResultXMIParser resultXMIParser = new ULResultXMIParser();

        for (File file : resultXMIParser.getFiles()) {

            String source = FileUtils.readFromFile(file);

            List<ULResult> resultPatterns = resultXMIParser.parse(source);

            CLIResultsRenderer resultPatternRenderer = new CLIResultsRenderer();
            String table = resultPatternRenderer.renderTable(resultPatterns);
            System.out.print(table);
        }
        */
  }


}
