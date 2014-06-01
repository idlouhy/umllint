package net.umllint.tool.views;

import net.umllint.common.ULContext;
import net.umllint.common.model.pattern.ULPattern;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class CLIPatternsRenderer {

  List<String> header = new LinkedList<String>();
  List<List<String>> table = new LinkedList<List<String>>();

  CLITableRenderer tableRenderer = new CLITableRenderer();
  CLIExportCSVRenderer exportCSVTableRenderer = new CLIExportCSVRenderer();

  private void render(List<ULPattern> patterns) {

    header.clear();
    table.clear();

    header.add("");
    header.add("ID");
    header.add("Title");
    header.add("Category");
    header.add("Severity");
    header.add("URL");


    for (ULPattern pattern : patterns) {

      List<String> row = new LinkedList<String>();



      String uri = String.format("umllint.patterns.%s.enabled", pattern.getId());
      String enabled = ULContext.instance().get(uri);
      if (enabled == null || enabled.contains("1")) {
        row.add("X");
      }
      else {
        row.add("");
      }

      row.add(pattern.getId().toString());
      row.add(pattern.getTitle());
      row.add(pattern.getCategory().getTitle());
      row.add(pattern.getSeverity().getTitle());
      String url = String.format("%s/app/pattern/view/%s", ULContext.instance().get("umllint.server"), pattern.getId());
      row.add(url);
      table.add(row);

    }
  }

  public String renderTable(List<ULPattern> patterns) {
    render(patterns);
    tableRenderer.setSeparator(" ");
    return tableRenderer.render(header, table);
  }

  public String renderExportCSV(List<ULPattern> patterns) {
    render(patterns);
    return exportCSVTableRenderer.render(header, table);
  }


}
