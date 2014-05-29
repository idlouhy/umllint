package net.umllint.tool.parsers;

import net.umllint.common.FileUtils;
import net.umllint.tool.model.uml.UMLDocument;
import net.umllint.tool.model.uml.UMLModel;
import net.umllint.tool.model.uml.classes.UMLRealization;
import net.umllint.tool.model.uml.classes.UMLClass;
import net.umllint.tool.model.uml.classes.UMLEnumeration;
import net.umllint.tool.model.uml.classes.UMLInterface;
import net.umllint.tool.model.uml.relations.UMLAssociation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public class ULXMIParser {

    private List<File> files = new LinkedList<File>();


    public ULXMIParser() {

        /*
        files.add(new File("test/resources/vp_xmi-1.0.xmi"));
        files.add(new File("test/resources/vp_xmi-1.2.xmi"));
        files.add(new File("test/resources/vp_xmi-2.1_simple.xmi"));
        files.add(new File("test/resources/vp_xmi-2.1_uml2.xmi"));

        files.add(new File("test/resources/altova_xmi-2.4.1_uml-2.4.1.xmi"));
        files.add(new File("test/resources/altova_xmi-2.1_uml-2.0.xmi"));

        files.add(new File("test/resources/test.xmi"));
        files.add(new File("test/resources/uml.xmi"));
        files.add(new File("test/resources/generalization-cycle.xmi"));
        files.add(new File("test/resources/vp_xmi-2.1_simple_extended.xmi"));
        */




        files.add(new File("resources/xmi/source.xmi"));


    }

    public List<File> getFiles() {
        return files;
    }

    public UMLDocument parseXMI(String source) throws IOException, SAXException, ParserConfigurationException {

        InputSource is = new InputSource(new ByteArrayInputStream(source.getBytes("utf-8")));

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        document.getDocumentElement().normalize();

        Element xmiElement = document.getDocumentElement();

        UMLDocument umlDocument = XMIParserLegacy.parseDocument(xmiElement);

        Element umlModelElement = (Element) xmiElement.getElementsByTagName("uml:Model").item(0);
        if (umlModelElement != null) {

            UMLModel umlModel = XMIParserLegacy.parseModel(umlModelElement);

            NodeList umlModelChildNodes = xmiElement.getElementsByTagName("ownedMember");
            for (int i = 0; i < umlModelChildNodes.getLength(); i++) {
                Node umlModelChildNode = umlModelChildNodes.item(i);

                Element umlModelChildElement = (Element) umlModelChildNode;

                String type = umlModelChildElement.getAttribute("xmi:type");

                if (type.equals("uml:Class")) {
                    UMLClass umlClass = XMIParserLegacy.parseClass(umlModelChildElement);
                    umlModel.addEntity(umlClass);
                } else if (type.equals("uml:Enumeration")) {
                    UMLEnumeration umlEnumeration = XMIParserLegacy.parseEnumeration(umlModelChildElement);
                    umlModel.addEntity(umlEnumeration);
                } else if (type.equals("uml:Interface")) {
                    UMLInterface umlInterface = XMIParserLegacy.parseInterface(umlModelChildElement);
                    umlModel.addEntity(umlInterface);
                } else if (type.equals("uml:Realization")) {
                    UMLRealization umlRealization = XMIParserLegacy.parseRealization(umlModelChildElement);
                    umlModel.addEntity(umlRealization);

                } else if (type.equals("uml:Association")) {
                    UMLAssociation umlAssociation = XMIParserLegacy.parseAssociation(umlModelChildElement);
                    umlModel.addEntity(umlAssociation);
                } else {
                    System.out.println(String.format("No parser for xmi:type %s (%s)", type, umlModelChildElement.getAttribute("name")));
                }
            }
            umlDocument.setUmlModel(umlModel);
        }

        return umlDocument;
    }


    public static void main(String[] args) throws Exception {

        ULXMIParser ULXMIParser = new ULXMIParser();

        for (File file : ULXMIParser.getFiles()) {

            String source = FileUtils.readFromFile(file);
            System.out.println(String.format("%nLoading file %s...", file.getName()));
            //        files.add(new File("resources/xmi/source.xmi"));

            UMLDocument UMLDocument = ULXMIParser.parseXMI(source);

            System.out.print(UMLDocument.toString());
            System.out.println();
        }

    }

}
