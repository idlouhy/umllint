package net.umllint.tool;

import net.umllint.common.ULContext;
import net.umllint.common.ULException;
import net.umllint.common.ULLog;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLLintTool {

  ULContext context = ULContext.instance();
  UMLLintToolController controller = new UMLLintToolController();


  public void run(String[] args) throws ULException {
    controller.handleArguments(args);
    context.load();
    controller.execute();
  }


  public static void main(String[] args) throws Exception {

    UMLLintTool tool = new UMLLintTool();

    try {
      tool.run(args);
    }
    catch (ULException e) {
      System.exit(1);
    }
    catch (Exception e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, String.format("%s", e.getMessage()));
    }

  }
}
