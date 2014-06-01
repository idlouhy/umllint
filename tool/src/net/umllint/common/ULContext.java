package net.umllint.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULContext {

  private static ULContext instance = null;
  private Map<String,String> context = new HashMap<String, String>();
  private final Properties properties = new Properties();


  protected ULContext() {
    set("umllint.server.api", "http://umllint.net/app/api");
    set("umllint.workspace", "workspace");
    set("umllint.input", "workspace/input.xmi");
    set("umllint.file.library", "resources/library.qvt");
    set("umllint.file.model.uml", "resources/uml.ecore");
    set("umllint.file.model.umllint", "resources/umllint.ecore");
    set("umllint.file.patterns", "resources/patterns.xml");
    set("umllint.file.config", "umllint.properties");
    set("umllint.debug.qvt", "true");
  }

  public static ULContext instance() {

    if (instance == null) {
      instance = new ULContext();
    }

    return instance;
  }

  public void set(String key, String value) {
    context.put(key, value);
  }

  public String get(String key) {

    String value = properties.getProperty(key);

    if (value == null) {
      value = context.get(key);
    }

    return value;
  }

  public Boolean getBoolean(String key) {

    String value = get(key);
    return (value != null && value.matches("true"));
  }

  public void load() {
    String filename = get("umllint.file.config");

    try {
      InputStream stream = new FileInputStream(filename);
      properties.load(stream);
      ULLog.getInstance().log(ULLog.LogLevel.INFO, String.format("Config %s loaded", filename));
    }
    catch (FileNotFoundException e) {
      ULLog.getInstance().log(ULLog.LogLevel.WARNING, String.format("Could not load config file (%s)", e.getMessage()));
    }
    catch (IOException e) {
      ULLog.getInstance().log(ULLog.LogLevel.WARNING, String.format("Could not load config file (%s)", e.getMessage()));
    }

  }

}
