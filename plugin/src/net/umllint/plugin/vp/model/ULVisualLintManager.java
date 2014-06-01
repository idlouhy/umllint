package net.umllint.plugin.vp.model;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULVisualLintManager {

  private List<ULVisualLint> lints = new LinkedList<ULVisualLint>();

  public ULVisualLintManager() {

  }

  public ULVisualLint getLint(int n) {
    return lints.get(n);
  }

  public List<ULVisualLint> getLints() {
    return lints;
  }

  public void addLint(ULVisualLint lint) {
    this.lints.add(lint);
  }

}