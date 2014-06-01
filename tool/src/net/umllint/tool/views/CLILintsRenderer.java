package net.umllint.tool.views;

import net.umllint.common.model.lint.ULLint;
import net.umllint.common.model.lint.ULLintManager;
import net.umllint.common.model.result.model.ULResultBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class CLILintsRenderer {

  List<String> header = new LinkedList<String>();
  List<List<String>> table = new LinkedList<List<String>>();

  CLITableRenderer tableRenderer = new CLITableRenderer();
  CLIExportCSVRenderer exportCSVTableRenderer = new CLIExportCSVRenderer();

  private void render(ULLintManager lints) {

    header.clear();
    table.clear();

    header.add("ID");
    header.add("Name");
    header.add("Category");
    header.add("Severity");
    header.add("Binding");
    header.add("Path");

    for (ULLint lint : lints.getLints()) {

      List<String> row = new LinkedList<String>();

      row.add(lint.getPattern().getId());
      row.add(lint.getPattern().getName());
      row.add(lint.getPattern().getCategory().getTitle());
      row.add(lint.getPattern().getSeverity().getTitle());
      row.add(lint.getBinding().getName());

      //render element pointer
      StringBuilder s = new StringBuilder();
      //s.append(UMLEntityDirectory.getInstance().resolveXmiId(binding.getElement().getId()));
      s.append(lint.getBinding().getName());

      ULResultBinding current = lint.getBinding();
      while (current.getBinding() != null) {
        s.append(" > ");
        //s.append(UMLEntityDirectory.getInstance().resolveXmiId(current.getElement().getId()));
        s.append(current.getBinding().getName());
        current = current.getBinding();
      }

      row.add(s.toString());
      table.add(row);
    }
  }

  public String renderTable(ULLintManager lints) {
    render(lints);
    tableRenderer.setSeparator(" ");
    return tableRenderer.render(header, table);
  }

  public String renderExportCSV(ULLintManager lints) {
    render(lints);
    return exportCSVTableRenderer.render(header, table);
  }

}
