package net.umllint.tool.misc;

import net.umllint.common.FileUtils;
import net.umllint.tool.model.pattern.ULPattern;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by idlouhy on 4/3/14.
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
      String qvt = FileUtils.readFromFile(new File("library.qvt"));
      out.append(qvt);

    } catch (IOException e) {
      e.printStackTrace();
    }


    out.append(String.format("}%n"));

    return out.toString();
  }

}
