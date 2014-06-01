package net.umllint.common.model.xml.parser;

import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLDocument;
import net.umllint.common.model.xml.model.XMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class XMLRenderer {

  public String render(XMLDocument xmlDocument) {

    StringBuilder builder = new StringBuilder();

    builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    builder.append(render(xmlDocument.getRoot(), 2));

    return builder.toString();
  }

  private String render(XMLElement xmlElement, Integer indentLevel) {

    StringBuilder builder = new StringBuilder();
    String indentBy = "  ";

    for (int i = 0; i < indentLevel; i++) {
      builder.append(indentBy);
    }

    builder.append("<"+xmlElement.getName());

    for (XMLAttribute xmlAttribute : xmlElement.getAttributes()) {
      builder.append(" ");
      builder.append(xmlAttribute.getName());
      builder.append("=");
      builder.append("\"");
      builder.append(xmlAttribute.getValue());
      builder.append("\"");
    }

    builder.append(">");

    for (XMLElement xmlElementChild : xmlElement.getElements()) {
      builder.append(render(xmlElementChild, indentLevel + 1));
    }

    builder.append("</"+xmlElement.getName()+">");

    return builder.toString();
  }
}
