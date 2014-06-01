package net.umllint.common.model.pattern.parser;

import net.umllint.common.FileUtils;
import net.umllint.common.ULLog;
import net.umllint.common.XMLUtils;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.pattern.ULPatternManager;
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

  public void update(File file, URL url) throws IOException {
    OutputStream stream = new FileOutputStream(file);
    FileUtils.simpleDownload(url, stream);
  }

  public ULPatternManager load(File file) throws IOException, ParserConfigurationException, SAXException {

    ULPatternManager manager = new ULPatternManager();

    String xml = FileUtils.readFromFile(file);
    List<ULPattern> patterns = XMLUtils.getPatterns(xml);

    for (ULPattern pattern : patterns) {
      ULLog.getInstance().log(ULLog.LogLevel.DEBUG, String.format("Loading %s", pattern.getTitle()));
      manager.addPattern(pattern);
    }

    return manager;
  }

}