package net.umllint.plugin.vp;

import com.vp.plugin.ApplicationManager;
import net.umllint.common.ULLog;

import java.io.*;
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

public class ULPluginContext {

  private static ULPluginContext instance = null;
  private Map<String,String> context = new HashMap<String, String>();
  private final Properties properties = new Properties();


  protected ULPluginContext() {
    set("umllint.server.api", "http://umllint.net/app/api");
    set("umllint.server", "http://umllint.net/");


    File plugin = ApplicationManager.instance().getPluginInfo("net.umllint.plugin.vp").getPluginDir();
    String path = plugin.getAbsolutePath();


    set("umllint.file.config", path+"/umllint.properties");

    set("umllint.workspace", path+"/workspace");
    set("umllint.input", path+"/workspace/input.xmi");
    set("umllint.file.library", path+"/resources/library.qvt");
    set("umllint.file.model.uml", path+"/resources/uml.ecore");
    set("umllint.file.model.umllint", path+"/resources/umllint.ecore");
    set("umllint.file.patterns", path+"/resources/patterns.xml");

    set("umllint.debug.qvt", "true");

  }

  public static ULPluginContext instance() {

    if (instance == null) {
      instance = new ULPluginContext();
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
