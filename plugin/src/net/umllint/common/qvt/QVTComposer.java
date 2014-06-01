package net.umllint.common.qvt;

import net.umllint.common.FileUtils;
import net.umllint.common.ULContext;
import net.umllint.common.model.pattern.ULPattern;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class QVTComposer {

  public String compose(List<ULPattern> patterns) {

    StringBuilder out = new StringBuilder();

    out.append(String.format("transformation lint(source:uml, target:umllint) {%n"));


    //dump patterns
    for (ULPattern pattern : patterns) {
      out.append(String.format("/* pattern: %s */%n", pattern.getName()));
      out.append(String.format("%s%n", pattern.getCode()));
      out.append(String.format("%n"));
    }

    //dump library
    try {
      String qvt = FileUtils.readFromFile(new File(ULContext.instance().get("umllint.file.library")));
      out.append(qvt);

    } catch (IOException e) {
      e.printStackTrace();
    }


    out.append(String.format("}%n"));

    return out.toString();
  }

}
