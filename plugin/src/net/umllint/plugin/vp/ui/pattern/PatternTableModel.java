package net.umllint.plugin.vp.ui.pattern;

import com.vp.plugin.model.IRelationship;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.plugin.vp.UMLLintManager;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatternTableModel extends AbstractTableModel {

  //private final IModelElement _base;
  //private final IRelationship[] _relationships;

  List<ULPattern> patterns;

  public PatternTableModel(List<ULPattern> patterns) {
    this.patterns = patterns;

    //IModelElement aBase, IRelationship[]

    //    _base = aBase;
    //    _relationships = aRelationships;
  }

  public static class Columns {
    public static final int
        Use = 0,
        PatternID = 1,
        PatternTitle = 2,
        PatternCategory = 3,
        PatternSeverity = 4,
        View = 5;
  }

  public IRelationship getPattern(int aRowIndex) {
    //return _relationships[aRowIndex];
    return null;
  }

  public int getColumnCount() {
    return 6;
  }

  public Class getColumnClass(int aColumnIndex) {
    switch (aColumnIndex) {
      case Columns.Use:
        return Boolean.class;
      case Columns.PatternID:
        return String.class;
      case Columns.PatternTitle:
        return String.class;
      case Columns.PatternCategory:
        return String.class;
      case Columns.PatternSeverity:
        return String.class;
      case Columns.View:
        return String.class;
    }
    return null;
  }

  public String getColumnName(int aColumnIndex) {
    switch (aColumnIndex) {
      case Columns.Use:
        return "";
      case Columns.PatternID:
        return "ID";
      case Columns.PatternTitle:
        return "Title";
      case Columns.PatternCategory:
        return "Category";
      case Columns.PatternSeverity:
        return "Severity";
      case Columns.View:
        return "";
    }
    return null;
  }

  public int getRowCount() {
    return patterns.size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {

    ULPattern pattern = patterns.get(rowIndex);

    switch (columnIndex) {
      case Columns.Use:
        return UMLLintManager.instance().mask.get(rowIndex);
      case Columns.PatternID:
        return pattern.getId();
      case Columns.PatternTitle:
        return pattern.getTitle();
      case Columns.PatternCategory:
        return pattern.getCategory().getTitle();
      case Columns.PatternSeverity:
        return pattern.getSeverity().getTitle();
      case Columns.View:
        return "<html><a href=\"umllint.net\">view online</a></html>";
      default:
        return "";
    }

  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return (columnIndex == Columns.Use);
  }

  public void setValueAt(Object value, int rowIndex, int columnIndex) {

    if (columnIndex == Columns.Use && value instanceof Boolean) {
      UMLLintManager.instance().mask.set(rowIndex, (Boolean) value);
    }

  }

}
