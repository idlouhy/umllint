package net.umllint.tool;


import net.umllint.tool.model.result.ULResult;
import net.umllint.tool.model.result.ULResultBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ULResultManager {

  private List<ULResult> results = new LinkedList<ULResult>();

  public ULResultManager() {

  }

  public List<ULResult> getResults() {
    return results;
  }

  public List<ULResult> getUniqueResults() {

    List<ULResult> r = new ArrayList<ULResult>();

    for (ULResult result : results) {

      Boolean duplicate = false;

      //check if there already is this pattern-element combination in results
      for (ULResult candidate : r) {
        if (candidate.getId().matches(result.getId())) {
          ULResultBinding candidateBinding = candidate.getBindings().get(0);
          ULResultBinding resultBinding = result.getBindings().get(0);
          if (candidateBinding.getId().matches(resultBinding.getId())) {
            duplicate = true;
            break;
          }
        }
      }

      if (!duplicate) {
        r.add(result);
      }

    }

    return r;
  }


  public void add(ULResult result) {
    this.results.add(result);
  }


}