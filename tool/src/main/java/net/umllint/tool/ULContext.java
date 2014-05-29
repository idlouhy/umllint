package net.umllint.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */

public class ULContext {

  private static ULContext instance = null;
  private Map<String,String> context = new HashMap<String, String>();
  private final Properties properties = new Properties();


  protected ULContext() {
    set("umllint.file.input", "input.xmi");
    set("umllint.file.database", "patterns.xml");
    set("umllint.file.config", "umllint.properties");
    set("umllint.file.patterns", "patterns.properties");
    set("umllint.file.workspace", "");


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
    String value = context.get(key);

    if (value == null) {
      value = properties.getProperty(key);
    }

    return value;
  }

  public void load() throws IOException {
    String filename = get("umllint.file.config");

    String message = String.format("Loading config: %s%n", filename);
    System.out.print(message);

    InputStream stream = new FileInputStream(filename);
    properties.load(stream);


    filename = get("umllint.file.patterns");

    message = String.format("Loading patterns: %s%n", filename);
    System.out.print(message);

    stream = new FileInputStream(filename);
    properties.load(stream);
  }

}
