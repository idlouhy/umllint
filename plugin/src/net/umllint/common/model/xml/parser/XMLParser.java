package net.umllint.common.model.xml.parser;

import net.umllint.common.XMLUtils;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLDocument;
import net.umllint.common.model.xml.model.XMLElement;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class XMLParser {

  private List<File> files = new LinkedList<File>();
  private List<String> blacklists = new LinkedList<String>();

  public XMLParser() {
  }


  public List<File> getFiles() {
    return files;
  }

  public void setFiles(List<File> files) {
    this.files = files;
  }

  public List<String> getBlacklists() {
    return blacklists;
  }

  public void setBlacklists(List<String> blacklists) {
    this.blacklists = blacklists;
  }

  public void addBlacklist(String blacklist) {
    this.blacklists.add(blacklist);
  }

  public void addFile(List<File> files) {
    this.files = files;
  }

  public XMLAttribute parseAttribute(Attr attribute) {
    return new XMLAttribute(attribute.getName(), attribute.getValue());
  }


  public XMLElement parseElement(Element element) {

    XMLElement xmlElement = new XMLElement(element.getTagName());

    for (Attr attribute : XMLUtils.getAttributes(element)) {

      XMLAttribute xmlAttribute = parseAttribute(attribute);
      xmlElement.addAttribute(xmlAttribute);
    }


    for (Element elementChild : XMLUtils.getChildElements(element)) {

      if (!blacklists.contains(elementChild.getTagName())) {
        XMLElement xmlElementChild = parseElement(elementChild);
        xmlElement.addElement(xmlElementChild);
      }
    }

    return xmlElement;
  }

  public XMLDocument parseDocument(Document document) {

    XMLDocument xmlDocument = new XMLDocument();
    xmlDocument.setVersion(document.getXmlVersion());

    //parse document element
    Element documentElement = document.getDocumentElement();
    xmlDocument.setRoot(parseElement(documentElement));

    return xmlDocument;
  }

  public XMLDocument parseDocument(String source) throws IOException, ParserConfigurationException, SAXException {

    InputSource is = new InputSource(new ByteArrayInputStream(source.getBytes("utf-8")));

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(is);
    document.getDocumentElement().normalize();

    XMLDocument xmlDocument = parseDocument(document);

    return xmlDocument;
  }

}
