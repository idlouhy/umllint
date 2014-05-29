package net.umllint.tool.parsers;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLDocument;
import net.umllint.tool.model.xml.XMLElement;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import java.io.*;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */

public class XMLRenderer {

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;

    public XMLRenderer() throws ParserConfigurationException {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    private void initDocument() {

        document.getDocumentElement().setAttribute("xmi:version", "2.1");


        //document.getDocumentElement().setAttribute("xmlns:xmi", "http://schema.omg.org/spec/XMI/2.1");
        document.getDocumentElement().setAttribute("xmlns:xmi", "http://www.omg.org/spec/XMI/20071001");
        document.getDocumentElement().setAttribute("xmlns:uml", "http://www.eclipse.org/uml2/2.1.0/UML");
        document.getDocumentElement().setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        document.getDocumentElement().setAttribute("xmlns:umllint", "http://umllint.dlouho.net/schema/0.7/umllint.ecore");

        document.getDocumentElement().setAttribute("xsi:schemaLocation", "http://umllint.dlouho.net/schema/0.7/umllint.ecore ../models/umllint.ecore http://www.eclipse.org/uml2/2.1.0/UML ../models/uml.ecore");
    }

    public Element renderElement(XMLElement xmlElement) {
        Element element = document.createElement(xmlElement.getName());

        for (XMLAttribute xmlAttribute : xmlElement.getAttributes()) {
            if (xmlAttribute.getValue() != null && !xmlAttribute.getValue().trim().equals("")) {
                Attr attribute = renderAttribute(xmlAttribute);
                element.setAttributeNode(attribute);
            }
        }

        for (XMLElement xmlChildElement : xmlElement.getElements()) {
            Element childElement = renderElement(xmlChildElement);
            element.appendChild(childElement);
        }

        return element;
    }

    public Attr renderAttribute(XMLAttribute xmlAttribute) {
        Attr attribute = document.createAttribute(xmlAttribute.getName());
        attribute.setValue(xmlAttribute.getValue());
        return attribute;
    }

    public String renderDocument(XMLDocument xmlDocument) throws TransformerConfigurationException, IOException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        Element root = renderElement(xmlDocument.getRoot());
        document.appendChild(root);

        initDocument();

        return prettyFormat(document);
    }


    public String prettyFormat(Document document) throws IOException {


        OutputFormat format = new OutputFormat(document);
        //format.setLineWidth(160);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(document);

        return out.toString();
    }

}
