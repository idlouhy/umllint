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

public class CLIPatternDetailRenderer {

  List<String> header = new LinkedList<String>();
  List<List<String>> table = new LinkedList<List<String>>();

  CLITableRenderer tableRenderer = new CLITableRenderer();

  private void render(ULPattern pattern) {

    header.clear();
    table.clear();

    header.add("");
    header.add("");

    renderKeyValue("id", pattern.getId());
    renderKeyValue("name", pattern.getName());
    renderKeyValue("title", pattern.getTitle());
    renderKeyValue("category", pattern.getCategory().getTitle());
    renderKeyValue("severity", pattern.getSeverity().getTitle());

    renderKeyValue("", "");
    renderKeyValue("reference", pattern.getReference().getTitle());
    String url = String.format("%s/app/pattern/view/%s", ULContext.instance().get("umllint.server"), pattern.getId());
    renderKeyValue("url", url);
  }

  public void renderKeyValue(String key, String value) {
    List<String> row = new LinkedList<String>();
    row.add(key);
    row.add(value);
    table.add(row);
  }

  public String renderTable(ULPattern pattern) {
    render(pattern);
    tableRenderer.setSeparator(" ");
    return tableRenderer.render(header, table);
  }

}
