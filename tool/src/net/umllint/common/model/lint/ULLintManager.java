package net.umllint.common.model.lint;

import java.util.LinkedList;
import java.util.List;

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