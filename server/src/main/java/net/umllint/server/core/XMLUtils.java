package net.umllint.server.core;

import net.umllint.server.model.ULPattern;
import net.umllint.server.model.ULPatternCategory;
import net.umllint.server.model.ULPatternReference;
import net.umllint.server.model.ULPatternSeverity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by idlouhy on 4/2/14.
 */
public class XMLUtils {

  public static List<ULPattern> getPatterns(String xml) throws ParserConfigurationException, IOException, SAXException {

    List<ULPattern> patterns = new LinkedList<ULPattern>();

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
    Document document = documentBuilder.parse(inputStream);

    document.getDocumentElement().normalize();


    Element patternsElement = document.getDocumentElement();

    NodeList patternNodes = patternsElement.getChildNodes();

    for (int i = 0; i < patternNodes.getLength(); i++) {


      Node patternNode = patternNodes.item(i);

      if (patternNode.getNodeType() == Node.ELEMENT_NODE) {

        Element patternElement = (Element) patternNode;

        ULPattern pattern = new ULPattern();
        pattern.setId(patternElement.getElementsByTagName("id").item(0).getTextContent());
        pattern.setName(patternElement.getElementsByTagName("name").item(0).getTextContent());
        pattern.setTitle(patternElement.getElementsByTagName("title").item(0).getTextContent());
        pattern.setDescription(patternElement.getElementsByTagName("description").item(0).getTextContent());
        pattern.setCode(patternElement.getElementsByTagName("code").item(0).getTextContent());

        ULPatternCategory category = new ULPatternCategory();
        category.setId(Integer.parseInt(patternElement.getElementsByTagName("category").item(0).getAttributes().getNamedItem("id").getTextContent()));
        category.setName(patternElement.getElementsByTagName("category").item(0).getAttributes().getNamedItem("name").getTextContent());
        category.setTitle(patternElement.getElementsByTagName("category").item(0).getTextContent());
        pattern.setCategory(category);

        ULPatternSeverity severity = new ULPatternSeverity();
        severity.setId(Integer.parseInt(patternElement.getElementsByTagName("severity").item(0).getAttributes().getNamedItem("id").getTextContent()));
        severity.setName(patternElement.getElementsByTagName("severity").item(0).getAttributes().getNamedItem("name").getTextContent());
        severity.setTitle(patternElement.getElementsByTagName("severity").item(0).getTextContent());
        pattern.setSeverity(severity);

        ULPatternReference reference = new ULPatternReference();
        reference.setId(Integer.parseInt(patternElement.getElementsByTagName("reference").item(0).getAttributes().getNamedItem("id").getTextContent()));
        reference.setName(patternElement.getElementsByTagName("reference").item(0).getAttributes().getNamedItem("name").getTextContent());
        reference.setTitle(patternElement.getElementsByTagName("reference").item(0).getTextContent());
        pattern.setReference(reference);

        patterns.add(pattern);

      }
    }

    return patterns;
  }

  public static String getPatternXML(List<ULPattern> patterns) {

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = null;
    try {
      docBuilder = docFactory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      //log.addMessage(e.getMessage());
    }

    Document document = docBuilder.newDocument();
    Element patternsElement = document.createElement("patterns");
    document.appendChild(patternsElement);


    for (ULPattern pattern : patterns) {

      if (pattern.getEnabled().contains("t")) {

        //pattern
        Element patternElement = document.createElement("pattern");
        patternsElement.appendChild(patternElement);

        //id
        Element idElement = document.createElement("id");
        idElement.setTextContent(pattern.getId().toString());
        patternElement.appendChild(idElement);

        //name
        Element nameElement = document.createElement("name");
        nameElement.setTextContent(pattern.getName());
        patternElement.appendChild(nameElement);

        //title
        Element titleElement = document.createElement("title");
        titleElement.setTextContent(pattern.getTitle());
        patternElement.appendChild(titleElement);

        //category
        Element categoryElement = document.createElement("category");
        categoryElement.setTextContent(pattern.getCategory().getTitle());
        categoryElement.setAttribute("id", pattern.getCategory().getId().toString());
        categoryElement.setAttribute("name", pattern.getCategory().getName());
        patternElement.appendChild(categoryElement);

        //severity
        Element severityElement = document.createElement("severity");
        severityElement.setTextContent(pattern.getSeverity().getTitle());
        severityElement.setAttribute("id", pattern.getSeverity().getId().toString());
        severityElement.setAttribute("name", pattern.getSeverity().getName());
        patternElement.appendChild(severityElement);

        //reference
        Element referenceElement = document.createElement("reference");
        referenceElement.setTextContent(pattern.getReference().getTitle());
        referenceElement.setAttribute("id", pattern.getReference().getId().toString());
        referenceElement.setAttribute("name", pattern.getReference().getName());
        referenceElement.setAttribute("citation", pattern.getReference().getCitation());
        patternElement.appendChild(referenceElement);

        //description
        Element descriptionElement = document.createElement("description");
        descriptionElement.setTextContent(pattern.getDescription());
        patternElement.appendChild(descriptionElement);

        //code
        Element codeElement = document.createElement("code");
        codeElement.setTextContent(pattern.getCode());
        patternElement.appendChild(codeElement);

      }

    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    try {
      Transformer transformer = transformerFactory.newTransformer();
      StringWriter writer = new StringWriter();
      DOMSource source = new DOMSource(document);

      transformer.transform(source, new StreamResult(writer));
      String xml = writer.toString();
      //log.addMessage(xml);

      return xml;

    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }

    return null;
  }


}
