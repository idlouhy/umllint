package net.umllint.plugin.vp.model;

import com.vp.plugin.diagram.IDiagramElement;
import net.umllint.common.model.lint.ULLint;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULVisualLint extends ULLint {

  private IDiagramElement diagramElement;

  public IDiagramElement getDiagramElement() {
    return diagramElement;
  }

  public void setDiagramElement(IDiagramElement diagramElement) {
    this.diagramElement = diagramElement;
  }
}
