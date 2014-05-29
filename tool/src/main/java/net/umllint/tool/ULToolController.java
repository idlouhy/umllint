package net.umllint.tool;

import net.umllint.common.FileUtils;
import net.umllint.tool.cli.ULCommandLineArguments;
import net.umllint.tool.mediniqvt.ULMediniQVTWrapper;
import net.umllint.tool.misc.QVTComposer;
import net.umllint.tool.misc.ULResultXMIParser;
import net.umllint.tool.views.CLIPatternDetailRenderer;
import net.umllint.tool.views.CLIPatternsRenderer;
import net.umllint.tool.views.CLIResultsRenderer;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */

public class ULToolController {

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
  ULPatternManager manager = null;
  ULMode mode = ULMode.HELP;


  public void analyze() throws ParserConfigurationException, SAXException, IOException {

    String message = String.format("Mode: analyze%n");
    System.out.print(message);

    manager = database.load();
    QVTComposer composer = new QVTComposer();
    String qvt = composer.compose(manager.getPatterns());

    FileUtils.writeToFile(new File("umllint.qvt"), qvt);

    //System.out.print(qvt);

    ULMediniQVTWrapper wrapper = new ULMediniQVTWrapper();
    wrapper.execute();

    String source = FileUtils.readFromFile(new File("target.xmi"));
    ULResultXMIParser resultXMIParser = new ULResultXMIParser();

    ULResultManager results = resultXMIParser.parse(source);


    CLIResultsRenderer resultPatternRenderer = new CLIResultsRenderer();
    String table = resultPatternRenderer.renderTable(results.getResults());
    System.out.print(table);
  }

  public void list() throws ParserConfigurationException, SAXException, IOException {
    String message = String.format("Mode: list patterns%n");
    System.out.print(message);

    manager = database.load();
    CLIPatternsRenderer patternRenderer = new CLIPatternsRenderer();
    String table = patternRenderer.renderTable(manager.getPatterns());
    System.out.print(table);
  }

  public void pattern() throws ParserConfigurationException, SAXException, IOException {
    String message = String.format("Mode: view pattern%n");
    System.out.print(message);

    manager = database.load();
    CLIPatternDetailRenderer renderer = new CLIPatternDetailRenderer();
    String table = renderer.renderTable(manager.getPatterns().get(0));
    System.out.print(table);
  }


  public void update() throws ParserConfigurationException, SAXException, IOException {
    String message = String.format("Mode: update%n");
    System.out.print(message);

    database.update();
    database.load();
  }


  public void enable() throws ParserConfigurationException, SAXException, IOException {
    String message = String.format("Mode: enable%n");
    System.out.print(message);
  }

  public void ignore() throws ParserConfigurationException, SAXException, IOException {
    String message = String.format("Mode: ignore%n");
    System.out.print(message);
  }


  public void execute() throws Exception {

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
        throw new Exception("Not implemented");
    }

  }

  public void handleArguments(String[] args) throws ParseException {
    try {
      mode = arguments.setUp(args);
    }
    catch (MissingOptionException e) {
      String message = String.format("%s%n%n", e.getMessage());
      System.out.print(message);
    }

  }

}
