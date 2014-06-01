package net.umllint.common;

import java.util.LinkedList;
import java.util.List;

/**
 * UMLLint
 * A Tool for Checking Correctness of Design Diagrams in UML
 *
 * Ivo Dlouhy
 * xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 */

public class ULLog {

  private static ULLog instance = null;
  protected List<LogMessage> logs = new LinkedList<LogMessage>();

  public enum LogLevel {
    ERROR,
    WARNING,
    INFO,
    DEBUG
  }

  private class LogMessage {

    private LogLevel level;
    private String message;

    public LogMessage(LogLevel level, String message) {
      this.level = level;
      this.message = message;
    }

    public LogLevel getLevel() {
      return level;
    }

    public String getMessage() {
      return message;
    }
  }

  protected ULLog() {

  }

  public static ULLog getInstance() {

    if (instance == null) {
      instance = new ULLog();
    }

    return instance;

  }

  public void log(LogLevel level, String message) {
    LogMessage log = new LogMessage(level, message);
    print(log);
    logs.add(log);
  }

  private void print(LogMessage log) {
    String message = String.format("[%S] %s%n", log.getLevel(), log.getMessage());
    System.out.print(message);
  }

}
