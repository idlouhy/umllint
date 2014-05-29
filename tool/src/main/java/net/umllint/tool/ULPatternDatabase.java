package net.umllint.tool;

import net.umllint.common.FileUtils;
import net.umllint.common.XMLUtils;
import net.umllint.tool.model.pattern.ULPattern;
import net.umllint.tool.networking.FileDownloader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULPatternDatabase {

  public void update() throws IOException {

    String link = String.format("%s/xml/patterns.xml", ULContext.instance().get("umllint.server.api"));
    URL url = new URL(link);

    String filename = ULContext.instance().get("umllint.file.database");
    OutputStream stream = new FileOutputStream(new File(filename));

    FileDownloader.simpleDownload(url, stream);

  }

  public ULPatternManager load() throws IOException, ParserConfigurationException, SAXException {

    ULPatternManager manager = new ULPatternManager();

    String filename = String.format("%s", ULContext.instance().get("umllint.file.database"));
    File file = new File(filename);

    String xml = FileUtils.readFromFile(file);
    List<ULPattern> patterns = XMLUtils.getPatterns(xml);

    for (ULPattern pattern : patterns) {
      String message = String.format("Loading pattern: '%s'", pattern.getTitle());
      System.out.println(message);
      manager.addPattern(pattern);
    }

    return manager;
  }

}