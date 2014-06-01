package net.umllint.plugin.vp.ui.result;

import com.vp.plugin.diagram.IDiagramElement;
import net.umllint.plugin.vp.model.ULVisualLint;
import net.umllint.plugin.vp.model.ULVisualLintManager;

import javax.swing.table.AbstractTableModel;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ResultTableModel extends AbstractTableModel {

  private ULVisualLintManager lintManager;

  public ResultTableModel(ULVisualLintManager lintManager) {
    this.lintManager = lintManager;
  }

  public static class Columns {
    public static final int
        PatternID = 0,
        PatternTitle = 1,
        PatternSeverity = 2,
        ResultBinding = 3,
        PatternCategory = 4,
        Links = 5;
  }

  public ULVisualLint getLint(int rowIndex) {
    return lintManager.getLint(rowIndex);
  }

  public int getColumnCount() {
    return 6;
  }

  public Class getColumnClass(int columnIndex) {
    switch (columnIndex) {
      case Columns.PatternID:
        return String.class;
      case Columns.PatternTitle:
        return String.class;
      case Columns.PatternSeverity:
        return String.class;
      case Columns.ResultBinding:
        return IDiagramElement.class;
      case Columns.PatternCategory:
        return String.class;
      case Columns.Links:
        return String.class;
    }
    return null;
  }

  public String getColumnName(int columnIndex) {
    switch (columnIndex) {
      case Columns.PatternID:
        return "ID";
      case Columns.PatternTitle:
        return "Title";
      case Columns.PatternSeverity:
        return "Severity";
      case Columns.ResultBinding:
        return "Binding";
      case Columns.PatternCategory:
        return "Category";
      case Columns.Links:
        return "";
    }
    return "";
  }

  public int getRowCount() {
    return lintManager.getLints().size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {

    ULVisualLint lint = getLint(rowIndex);

    switch (columnIndex) {
      case Columns.PatternID:
        return lint.getPattern().getId();
      case Columns.PatternTitle:
        return lint.getPattern().getTitle();
      case Columns.PatternSeverity:
        return lint.getPattern().getSeverity().getTitle();
      case Columns.ResultBinding:
        return lint.getDiagramElement();
      case Columns.PatternCategory:
        return lint.getPattern().getCategory();
      case Columns.Links:
        return "<html><b><a href=\"#\">details</a></b></html>";
    }

    return null;
  }

  public boolean isCellEditable(int aRowIndex, int aColumnIndex) {
    return false;
  }

  public void setValueAt(Object value, int rowIndex, int columnIndex) {
  }

}
