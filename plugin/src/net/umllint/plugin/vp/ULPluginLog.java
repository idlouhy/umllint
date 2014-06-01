package net.umllint.plugin.vp;

import com.vp.plugin.ApplicationManager;
import net.umllint.common.*;

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

public class ULPluginLog extends net.umllint.common.ULLog {

  private static ULPluginLog instance = null;

  protected ULPluginLog() {

  }

  public static ULPluginLog getInstance() {
    if (instance == null) {
      instance = new ULPluginLog();
    }
    return instance;
  }


  @Override
  public void log(LogLevel level, String message) {
    LogMessage log = new LogMessage(level, message);
    print(log);
    logs.add(log);
  }

  private void print(LogMessage log) {
    String message = String.format("[%S] %s%n", log.getLevel(), log.getMessage());
    ApplicationManager.instance().getViewManager().showMessage(message);
  }

}
