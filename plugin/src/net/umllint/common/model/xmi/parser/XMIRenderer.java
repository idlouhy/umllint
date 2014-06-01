package net.umllint.common.model.xmi.parser;


import net.umllint.common.model.uml.UMLDocument;
import net.umllint.common.model.xml.model.XMLDocument;
import net.umllint.common.model.xml.parser.XMLRenderer;

import javax.xml.parsers.ParserConfigurationException;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class XMIRenderer extends XMLRenderer {

  public XMIRenderer() throws ParserConfigurationException {

  }

  public XMLDocument renderDocument(UMLDocument umlDocument) {
    return renderDocument(umlDocument);
  }
}
