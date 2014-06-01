package net.umllint.tool;

import net.umllint.common.*;
import net.umllint.common.model.lint.ULLint;
import net.umllint.common.model.lint.ULLintManager;
import net.umllint.common.model.pattern.ULPatternDatabase;
import net.umllint.common.model.pattern.ULPatternManager;
import net.umllint.common.model.result.model.ULResult;
import net.umllint.common.model.result.model.ULResultBinding;
import net.umllint.common.model.result.model.ULResultManager;
import net.umllint.common.model.result.parser.ULResultParser;
import net.umllint.common.qvt.MediniQVTIntegrationSetup;
import net.umllint.common.qvt.MediniQVTIntegrationWrapper;
import net.umllint.common.qvt.QVTComposer;
import net.umllint.tool.cli.ULCommandLineArguments;
import net.umllint.tool.views.CLILintsRenderer;
import net.umllint.tool.views.CLIPatternDetailRenderer;
import net.umllint.tool.views.CLIPatternsRenderer;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLLintToolController {

  public enum ULMode {
    HELP,
    LIST,
    PATTERN,
    ANALYZE,
    ENABLE,
    IGNORE,
    UPDATE
  }

  ULCommandLineArguments arguments = new ULCommandLineArguments();
  ULPatternDatabase database = new ULPatternDatabase();
  ULPatternManager patterns = null;
  ULMode mode = ULMode.HELP;


  public void analyze() throws ULException {

    try {
      ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: ANALYZE");

      File file = new File(ULContext.instance().get("umllint.file.patterns"));
      patterns = database.load(file);
      QVTComposer composer = new QVTComposer();
      String qvt = composer.compose(patterns.getPatterns());

      MediniQVTIntegrationSetup setup = new MediniQVTIntegrationSetup();
      MediniQVTIntegrationWrapper wrapper = new MediniQVTIntegrationWrapper(setup);

      String workspace = ULContext.instance().get("umllint.workspace");
      setup.setUmllint(ULContext.instance().get("umllint.file.model.umllint"));
      setup.setUml(ULContext.instance().get("umllint.file.model.uml"));
      setup.setQvt(workspace + "/umllint.qvt");
      setup.setSource(ULContext.instance().get("umllint.input"));
      setup.setTarget(workspace + "/umllint.xmi");
      setup.setOutput(new FileOutputStream(new File(workspace + "/transformation.log")));
      setup.setDebug(ULContext.instance().getBoolean("umllint.debug.qvt"));

      FileUtils.writeToFile(new File(workspace + "/umllint.qvt"), qvt);

      File umllintFile = new File(workspace + "/umllint.xmi");
      umllintFile.delete();



      String input = FileUtils.readFromFile(new File(ULContext.instance().get("umllint.input")));

      XMIPreProcessor xmiPreProcessor = new XMIPreProcessor();
      String output = xmiPreProcessor.process(input);

      FileUtils.writeToFile(new File(ULContext.instance().get("umllint.input")), output);

      wrapper.execute();


      String source = FileUtils.readFromFile(new File(workspace + "/umllint.xmi"));
      ULResultParser resultXMIParser = new ULResultParser();

      ULResultManager results = resultXMIParser.parse(source);

      //build ullints

      ULLintManager lints = new ULLintManager();
      for (ULResult result : results.getResults()) {
        for (ULResultBinding binding : result.getBindings()) {
          ULLint lint = new ULLint();
          lint.setResult(result);
          lint.setBinding(binding);
          lint.setPattern(patterns.getPatternById(result.getId()));
          lints.addLint(lint);
        }
      }

      CLILintsRenderer renderer = new CLILintsRenderer();
      String table = renderer.renderTable(lints);
      System.out.print(table);
    } catch (Throwable e) {

      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      //ULLog.getInstance().log(ULLog.LogLevel.DEBUG, e.toString());
      throw new ULException();
    }

  }

  public void list() throws ULException {
    ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: LIST PATTERNS");

    try {
      File file = new File(ULContext.instance().get("umllint.file.patterns"));
      patterns = database.load(file);
      CLIPatternsRenderer patternRenderer = new CLIPatternsRenderer();
      String table = patternRenderer.renderTable(patterns.getPatterns());
      System.out.print(table);
    } catch (Exception e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      throw new ULException();
    }

  }

  public void pattern() throws ULException {
    ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: VIEW PATTERN");

    try {
      File file = new File(ULContext.instance().get("umllint.file.patterns"));
      patterns = database.load(file);
      CLIPatternDetailRenderer renderer = new CLIPatternDetailRenderer();
      String table = renderer.renderTable(patterns.getPatterns().get(0));
      System.out.print(table);
    } catch (Exception e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      throw new ULException();
    }
  }


  public void update() throws ULException {
    ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: UPDATE");

    try {
      File file = new File(ULContext.instance().get("umllint.file.patterns"));
      URL url = new URL(String.format("%s/xml/patterns.xml", ULContext.instance().get("umllint.server.api")));
      database.update(file, url);
      database.load(file);
    } catch (Exception e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      throw new ULException();
    }

  }


  public void enable() throws ULException {
    ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: ENABLE");
    throw new ULException();
  }

  public void ignore() throws ULException {
    ULLog.getInstance().log(ULLog.LogLevel.DEBUG, "Mode: ENABLE");
    throw new ULException();
  }


  public void execute() throws ULException {

    switch (mode) {

      case ANALYZE:
        analyze();
        break;

      case LIST:
        list();
        break;

      case PATTERN:
        pattern();
        break;

      case UPDATE:
        update();
        break;

      case ENABLE:
        enable();
        break;

      case IGNORE:
        ignore();
        break;

      case HELP:
        arguments.printHelp();
        break;

      default:
        ULLog.getInstance().log(ULLog.LogLevel.WARNING, "Mode not implemented");
        throw new ULException();
    }

  }

  public void handleArguments(String[] args) throws ULException {
    try {
      mode = arguments.setUp(args);
    } catch (MissingOptionException e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      throw new ULException();

    } catch (ParseException e) {
      ULLog.getInstance().log(ULLog.LogLevel.ERROR, e.getMessage());
      throw new ULException();
    }

  }

}
