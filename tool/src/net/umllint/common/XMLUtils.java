package net.umllint.common;

import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.pattern.ULPatternCategory;
import net.umllint.common.model.pattern.ULPatternReference;
import net.umllint.common.model.pattern.ULPatternSeverity;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * UMLLint
 * A Tool for Checking Correctness of Design Diagrams in UML
 *
 * Ivo Dlouhy
 * xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 */

public class XMLUtils {


    public static List<Element> getElementsByTagName(Element element, String name) {

        List<Element> elements = new LinkedList<Element>();
        NodeList nodeList = element.getElementsByTagName(name);

        for (int i = 0; i < nodeList.getLength(); i++) {
            elements.add((Element) nodeList.item(i));
        }

        return elements;
    }


    public static Element getFirstChildElementByTagName(Element element, String name) {

        List<Element> firstChildElementsByTagName = getChildElementsByTagName(element, name);

        if (firstChildElementsByTagName.size() > 0) {
            return firstChildElementsByTagName.get(0);
        } else {
            return null;
        }

    }

    public static List<Element> getChildElementsByTagName(Element element, String name) {

        List<Element> elements = new LinkedList<Element>();

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                if (e.getTagName().equals(name)) {
                    elements.add(e);
                }
            }
        }

        return elements;
    }


    public static List<Element> getChildElements(Element element) {

        List<Element> elements = new LinkedList<Element>();

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                elements.add(e);
            }
        }

        return elements;
    }

    public static List<Attr> getAttributes(Element element) {

        List<Attr> attributes = new LinkedList<Attr>();

        NamedNodeMap namedNodeMap = element.getAttributes();

        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node node = namedNodeMap.item(i);
            if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
                Attr a = (Attr) node;
                attributes.add(a);
            }
        }

        return attributes;
    }


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
}
