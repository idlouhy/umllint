package net.umllint.plugin.vp;

import com.vp.plugin.ApplicationManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

@Deprecated
public class ULLog {

  private static ULLog instance = null;

  protected ULLog() {

  }

  public static ULLog getInstance() {
    if (instance == null) {
      instance = new ULLog();
    }
    return instance;
  }


  public static void l(String message) {
    ApplicationManager.instance().getViewManager().showMessage("[LOG] "+message);
  }

  public static void e(Exception e) {


    try {

      ByteArrayOutputStream os = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(os);
      e.printStackTrace(ps);
      String output = os.toString("UTF8");
      ApplicationManager.instance().getViewManager().showMessage(output);

    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }



  }

}
