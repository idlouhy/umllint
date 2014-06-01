package net.umllint.plugin.vp.ui.result;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.view.IDialog;
import com.vp.plugin.view.IDialogHandler;
import net.umllint.plugin.vp.model.ULVisualLint;
import net.umllint.plugin.vp.model.ULVisualLintManager;
import net.umllint.plugin.vp.ui.pattern.PatternDetailDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ResultDialog implements IDialogHandler {

  private final Component _component;
  private IDialog _dialog;

  private ULVisualLintManager lintManager;

  public ResultDialog(final ULVisualLintManager lintManager) {
    this.lintManager = lintManager;

    JLabel tableLabel = new JLabel("Pattern List:");
    ResultTableModel tableModel = new ResultTableModel(lintManager);
    JTable tableTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(tableTable);
    tableTable.getColumnModel().getColumn(ResultTableModel.Columns.ResultBinding).setCellRenderer(new DiagramElementCellRenderer());

    JButton closeButton = new JButton("Close");

    JPanel lPanel = new JPanel();
    {
      GridBagLayout lLayout = new GridBagLayout();
      lPanel.setLayout(lLayout);

      GridBagConstraints lCons = new GridBagConstraints();
      lCons.anchor = GridBagConstraints.CENTER;
      lCons.gridwidth = GridBagConstraints.REMAINDER;
      lLayout.setConstraints(closeButton, lCons);

      lCons.anchor = GridBagConstraints.WEST;
      lLayout.setConstraints(tableLabel, lCons);

      lCons.weightx = 1;
      lCons.weighty = 1;
      lCons.fill = GridBagConstraints.BOTH;
      lCons.insets.bottom = 10;
      lLayout.setConstraints(tableScrollPane, lCons);
    }

    lPanel.add(tableLabel);
    lPanel.add(tableScrollPane);
    lPanel.add(closeButton);

    _component = lPanel;


    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        _dialog.close();
      }
    });

    MouseListener resultTableMouseListener = new MouseListener() {
      public void mouseClicked(MouseEvent aE) {
        if (aE.getClickCount() > 1) {
          JTable lTable = (JTable) aE.getComponent();

          Point lLocation = aE.getPoint();

          IModelElement lModelElement = null;

          int lRow = lTable.rowAtPoint(lLocation);
          int lColumn = lTable.columnAtPoint(lLocation);

          if (lColumn == ResultTableModel.Columns.Links) {
            ULVisualLint lint = lintManager.getLint(lRow);
            ApplicationManager.instance().getViewManager().showDialog(new PatternDetailDialog(lint.getPattern()));
          }

          if (lColumn == ResultTableModel.Columns.ResultBinding) {
            ULVisualLint lint = lintManager.getLint(lRow);
            ApplicationManager.instance().getDiagramManager().highlight(lint.getDiagramElement());
          }
        }
      }

      public void mouseEntered(MouseEvent aE) {
      }

      public void mouseExited(MouseEvent aE) {
      }

      public void mousePressed(MouseEvent aE) {
      }

      public void mouseReleased(MouseEvent aE) {
      }
    };

    tableTable.addMouseListener(resultTableMouseListener);
  }

  public boolean canClosed() {
    return true;
  }

  public Component getComponent() {
    return _component;
  }

  public void prepare(IDialog aDialog) {
    _dialog = aDialog;

    aDialog.setSize(500, 450);
    aDialog.setTitle("List Patterns");
    aDialog.setModal(false);
  }

  public void shown() {
  }

  private static class PatternCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable aTable, Object aValue, boolean aIsSelected, boolean aHasFocus, int aRow, int aColumn) {
      Component lComponent = super.getTableCellRendererComponent(aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);

      if (lComponent instanceof JLabel && aValue instanceof String) {
        JLabel lLabel = (JLabel) lComponent;
        lLabel.setText((String) aValue);
      }

      return lComponent;
    }
  }

  private static class DiagramElementCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable aTable, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int columnIndex) {
      Component component = super.getTableCellRendererComponent(aTable, value, isSelected, hasFocus, rowIndex, columnIndex);

      if (component instanceof JLabel && value instanceof IDiagramElement) {

        IDiagramElement element = (IDiagramElement) value;

        ViewManager viewManager = ApplicationManager.instance().getViewManager();

        JLabel label = (JLabel) component;
        label.setText(element.getModelElement().getNickname());
        label.setIcon(viewManager.getIconByModel(element.getModelElement()));
      }

      return component;
    }
  }

}
