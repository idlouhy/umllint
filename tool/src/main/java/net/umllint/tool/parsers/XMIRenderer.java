package net.umllint.tool.parsers;

import net.umllint.tool.model.uml.UMLDocument;
import net.umllint.tool.model.uml.UMLModel;
import net.umllint.tool.model.xml.XMLDocument;
import net.umllint.tool.model.xml.XMLElement;

import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */


public class XMIRenderer extends XMLRenderer {

    public XMIRenderer() throws ParserConfigurationException {

    }

    public XMLDocument renderDocument(UMLDocument umlDocument) {

        XMLDocument xmlDocument = new XMLDocument();
        UMLModel umlModel = umlDocument.getUmlModel();
        XMLElement umlModelElement = umlModel.toXML();
        xmlDocument.setRoot(umlModelElement);
        return xmlDocument;
    }
}
