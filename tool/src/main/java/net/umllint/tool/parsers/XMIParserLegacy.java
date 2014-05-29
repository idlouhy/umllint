package net.umllint.tool.parsers;

import net.umllint.tool.model.uml.UMLAbstractEntity;
import net.umllint.tool.model.uml.UMLDocument;
import net.umllint.tool.model.uml.UMLModel;
import net.umllint.tool.model.uml.classes.*;
import net.umllint.tool.model.uml.classes.common.UMLAbstractClass;
import net.umllint.tool.model.uml.relations.UMLAssociation;
import net.umllint.tool.model.uml.relations.properties.UMLRelationEnd;
import net.umllint.tool.model.uml.relations.properties.UMLRelationEndMultiplicity;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 *
 */


public class XMIParserLegacy {

    public static UMLAbstractEntity parseAbstractEntity(Element element, UMLAbstractEntity umlAbstractEntity) {
        umlAbstractEntity.setName(element.getAttribute("name"));
        umlAbstractEntity.setXmiId(element.getAttribute("xmi:id"));
        return umlAbstractEntity;
    }

    public static UMLAbstractClass parseAbstractClass(Element element, UMLAbstractClass umlAbstractClass) {
        umlAbstractClass = (UMLAbstractClass) parseAbstractEntity(element, umlAbstractClass);
        umlAbstractClass.setVisibility(element.getAttribute("visibility"));
        umlAbstractClass.setIsAbstract(element.getAttribute("isAbstract"));
        umlAbstractClass.setIsActive(element.getAttribute("isActive"));
        umlAbstractClass.setIsLeaf(element.getAttribute("isLeaf"));

        //look for generalization
        NodeList generalizationNodes = element.getElementsByTagName("generalization");
        if (generalizationNodes.getLength() == 1) {
            Element generalizationElement = (Element) generalizationNodes.item(0);
            UMLGeneralization umlGeneralization = new UMLGeneralization();
            umlGeneralization = (UMLGeneralization) parseAbstractEntity(generalizationElement, umlGeneralization);
            umlGeneralization.setGeneral(generalizationElement.getAttribute("general"));
            umlAbstractClass.setGeneralization(umlGeneralization);
        }

        return umlAbstractClass;
    }

    // specific

    public static UMLDocument parseDocument(Element element) {
        UMLDocument umlDocument = new UMLDocument();
        umlDocument.setXmiNamespace(element.getAttribute("xmlns:xmi"));
        umlDocument.setUmlNamespace(element.getAttribute("xmlns:uml"));

        String version = element.getAttribute("xmi.version");
        if (version == null || version.equals("")) {
            version = element.getAttribute("xmi:version");
        }
        umlDocument.setXmiVersion(version);

        return umlDocument;
    }

    public static UMLModel parseModel(Element element) {
        UMLModel umlModel = new UMLModel();
        umlModel = (UMLModel) parseAbstractEntity(element, umlModel);
        return umlModel;
    }


    public static UMLClass parseClass(Element element) {
        UMLClass umlClass = new UMLClass();
        umlClass = (UMLClass) parseAbstractClass(element, umlClass);
        return umlClass;
    }

    public static UMLEnumeration parseEnumeration(Element element) {
        UMLEnumeration umlEnumeration = new UMLEnumeration();
        umlEnumeration = (UMLEnumeration) parseAbstractEntity(element, umlEnumeration);
        return umlEnumeration;
    }

    public static UMLInterface parseInterface(Element element) {
        UMLInterface umlInterface = new UMLInterface();
        umlInterface = (UMLInterface) parseAbstractEntity(element, umlInterface);
        return umlInterface;
    }

    public static UMLRealization parseRealization(Element element) {
        UMLRealization umlRealization = new UMLRealization();
        umlRealization = (UMLRealization) parseAbstractEntity(element, umlRealization);
        umlRealization.setClient(element.getAttribute("client"));
        umlRealization.setRealizingClassifier(element.getAttribute("realizingClassifier"));
        umlRealization.setSupplier(element.getAttribute("supplier"));
        return umlRealization;
    }

    public static UMLAssociation parseAssociation(Element element) {
        UMLAssociation umlAssociation = new UMLAssociation();
        umlAssociation = (UMLAssociation) parseAbstractEntity(element, umlAssociation);
        umlAssociation.setIsAbstract(element.getAttribute("isAbstract"));
        umlAssociation.setIsDerived(element.getAttribute("isDerived"));
        umlAssociation.setIsLeaf(element.getAttribute("isLeaf"));

        NodeList umlRelationEndNodes = element.getElementsByTagName("ownedEnd");
        if (umlRelationEndNodes.getLength() == 2) {

            Element umlRelationEnd1Element = (Element) umlRelationEndNodes.item(0);
            UMLRelationEnd umlRelationEnd1 = parseRelationEnd(umlRelationEnd1Element);
            umlAssociation.setRelationEnd1(umlRelationEnd1);

            Element umlRelationEnd2Element = (Element) umlRelationEndNodes.item(1);
            UMLRelationEnd umlRelationEnd2 = parseRelationEnd(umlRelationEnd2Element);
            umlAssociation.setRelationEnd2(umlRelationEnd2);
        }

        return umlAssociation;
    }

    public static UMLRelationEnd parseRelationEnd(Element element) {
        UMLRelationEnd umlRelationEnd = new UMLRelationEnd();
        umlRelationEnd = (UMLRelationEnd) parseAbstractEntity(element, umlRelationEnd);
        umlRelationEnd.setAggregation(element.getAttribute("aggregation"));
        umlRelationEnd.setAssociation(element.getAttribute("association"));
        umlRelationEnd.setIsDerived(element.getAttribute("isDerived"));
        umlRelationEnd.setIsNavigable(element.getAttribute("isNavigable"));
        umlRelationEnd.setType(element.getAttribute("type"));

        //lower value
        NodeList umlRelationEndLowerValueNodes = element.getElementsByTagName("lowerValue");
        if (umlRelationEndLowerValueNodes.getLength() == 1) {
            Element umlRelationEndLowerValueElement = (Element) umlRelationEndLowerValueNodes.item(0);
            UMLRelationEndMultiplicity umlRelationEndMultiplicity = new UMLRelationEndMultiplicity();
            umlRelationEndMultiplicity = (UMLRelationEndMultiplicity) parseAbstractEntity(umlRelationEndLowerValueElement, umlRelationEndMultiplicity);
            umlRelationEndMultiplicity.setValue(umlRelationEndLowerValueElement.getAttribute("value"));
            umlRelationEnd.setLowerMultiplicity(umlRelationEndMultiplicity);
        }

        //Upper value
        NodeList umlRelationEndUpperValueNodes = element.getElementsByTagName("upperValue");
        if (umlRelationEndUpperValueNodes.getLength() == 1) {
            Element umlRelationEndUpperValueElement = (Element) umlRelationEndUpperValueNodes.item(0);
            UMLRelationEndMultiplicity umlRelationEndMultiplicity = new UMLRelationEndMultiplicity();
            umlRelationEndMultiplicity = (UMLRelationEndMultiplicity) parseAbstractEntity(umlRelationEndUpperValueElement, umlRelationEndMultiplicity);
            umlRelationEndMultiplicity.setValue(umlRelationEndUpperValueElement.getAttribute("value"));
            umlRelationEnd.setUpperMultiplicity(umlRelationEndMultiplicity);
        }

        return umlRelationEnd;
    }

}
