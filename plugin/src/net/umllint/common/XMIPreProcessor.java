package net.umllint.common;

import net.umllint.common.model.xml.model.XMLDocument;
import net.umllint.common.model.xml.parser.XMLParser;
import net.umllint.common.model.xml.parser.XMLRenderer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class XMIPreProcessor {

  private Map<String, String> replace = new HashMap<String, String>();
  private List<String> blacklist = new LinkedList<String>();

  public XMIPreProcessor() {
    replace.put("xmlns:uml=\"http://schema.omg.org/spec/UML/2.0\"", "xmlns:uml=\"http://www.eclipse.org/uml2/2.1.0/UML\"");
    replace.put("ownedMember", "packagedElement");
    blacklist.add("xmi:Extension");
    blacklist.add("uml:Diagram");
    blacklist.add("xmi:Documentation");
  }

  private String replace(String input) {
    for (String key : replace.keySet()) {
      String from = key;
      String to = replace.get(key);
      input = input.replace(from, to);
    }
    return input;
  }

  private String filter(String input) throws ParserConfigurationException, SAXException, IOException {

    XMLParser xmlParser = new XMLParser();
    XMLRenderer xmlRenderer = new XMLRenderer();

    xmlParser.setBlacklists(blacklist);
    XMLDocument document = xmlParser.parseDocument(input);
    String output = xmlRenderer.render(document);

    return output;
  }

  public String process(String input) throws IOException, SAXException, ParserConfigurationException {
    return filter(replace(input));
  }


}
