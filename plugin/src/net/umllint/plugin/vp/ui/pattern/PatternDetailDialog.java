package net.umllint.plugin.vp.ui.pattern;

import com.vp.plugin.view.IDialog;
import com.vp.plugin.view.IDialogHandler;
import net.umllint.common.ULLog;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.plugin.vp.ULPluginLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class PatternDetailDialog implements IDialogHandler {

  private final Component _component;
  private IDialog _dialog;

  private ULPattern pattern;

  public PatternDetailDialog(final ULPattern pattern) {

    this.pattern = pattern;

    JLabel label = new JLabel("Pattern Detail:");

    String url = String.format("http://umllint.net/app/pattern/view/%s?remote", pattern.getId());
    ULPluginLog.getInstance().log(ULLog.LogLevel.DEBUG, String.format("Navigate to: %s", url));

    JEditorPane editorPane = null;
    try {
      editorPane = new JEditorPane(url);
    } catch (IOException e) {
      e.printStackTrace();
    }
    editorPane.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(editorPane);

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
      lLayout.setConstraints(label, lCons);

      lCons.weightx = 1;
      lCons.weighty = 1;
      lCons.fill = GridBagConstraints.BOTH;
      lCons.insets.bottom = 10;
      lLayout.setConstraints(scrollPane, lCons);
    }

    lPanel.add(label);
    lPanel.add(scrollPane);
    lPanel.add(closeButton);

    _component = lPanel;
    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        _dialog.close();
      }
    });

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

}
