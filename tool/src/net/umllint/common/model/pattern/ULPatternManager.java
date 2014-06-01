package net.umllint.common.model.pattern;

import java.util.LinkedList;
import java.util.List;

public class ULPatternManager {

  private List<ULPattern> patterns = new LinkedList<ULPattern>();

  public ULPatternManager() {

  }

  public List<ULPattern> getPatterns() {
    return patterns;
  }

  public ULPattern getPatternById(String id) {
    for (ULPattern pattern : patterns) {
      if (pattern.getId().matches(id)) {
        return pattern;
      }
    }
    return null;
  }

  public void addPattern(ULPattern pattern) {
    this.patterns.add(pattern);
  }
}