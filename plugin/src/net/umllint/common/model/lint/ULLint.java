package net.umllint.common.model.lint;

import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.result.model.ULResult;
import net.umllint.common.model.result.model.ULResultBinding;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULLint {

  private ULResult result;
  private ULPattern pattern;
  private ULResultBinding binding;

  public ULResult getResult() {
    return result;
  }

  public void setResult(ULResult result) {
    this.result = result;
  }

  public ULPattern getPattern() {
    return pattern;
  }

  public void setPattern(ULPattern pattern) {
    this.pattern = pattern;
  }

  public ULResultBinding getBinding() {
    return binding;
  }

  public void setBinding(ULResultBinding binding) {
    this.binding = binding;
  }
}
