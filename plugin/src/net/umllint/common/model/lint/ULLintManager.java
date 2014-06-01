package net.umllint.common.model.lint;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULLintManager {

  private List<ULLint> lints = new LinkedList<ULLint>();

  public ULLintManager() {

  }

  public ULLint getLint(int n) {
    return lints.get(n);
  }

  public List<ULLint> getLints() {
    return lints;
  }

  public void addLint(ULLint lint) {
    this.lints.add(lint);
  }

}