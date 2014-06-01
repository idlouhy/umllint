package net.umllint.plugin.vp;

import java.util.ArrayList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLLintManager {

  private static UMLLintManager instance;

  public List<Boolean> mask = new ArrayList<Boolean>();

  protected UMLLintManager() {

  }

  public static UMLLintManager instance() {

    if (instance == null) {
      instance = new UMLLintManager();
    }

    return instance;
  }

}
