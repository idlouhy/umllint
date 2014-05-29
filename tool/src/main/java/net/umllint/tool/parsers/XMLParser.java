package net.umllint.tool.parsers;

import net.umllint.common.XMLUtils;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLDocument;
import net.umllint.tool.model.xml.XMLElement;
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
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
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

    public void pprint(XMLDocument xmlDocument) {
        System.out.print(" ");
        System.out.print(xmlDocument.toString());
        System.out.println();

        pprint(xmlDocument.getRoot(), 2);
    }

    private void pprint(XMLElement xmlElement, Integer indentLevel) {

        String indentBy = "  ";

        for (int i = 0; i < indentLevel; i++) {
            System.out.print(indentBy);
        }

        System.out.print(xmlElement.toString());

        for (XMLAttribute xmlAttribute : xmlElement.getAttributes()) {
            System.out.print(" ");
            System.out.print(xmlAttribute.toString());
            System.out.print(" ");
        }

        System.out.println();

        for (XMLElement xmlElementChild : xmlElement.getElements()) {
            pprint(xmlElementChild, indentLevel + 1);
        }
    }
}
