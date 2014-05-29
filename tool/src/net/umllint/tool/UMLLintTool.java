package net.umllint.tool;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLLintTool {



  public static void main(String[] args) throws Exception {

    ULContext context = ULContext.instance();

    ULToolController controller = new ULToolController();
    controller.handleArguments(args);

    try {
      context.load();
    }
    catch (Exception e) {
      String message = String.format("Config file not found!");
      System.out.print(message);
    }

    controller.execute();
  }
}
