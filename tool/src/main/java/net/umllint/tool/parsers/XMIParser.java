package net.umllint.tool.parsers;

import net.umllint.tool.model.uml.*;
import net.umllint.tool.model.uml.classes.*;
import net.umllint.tool.model.uml.relations.UMLAssociation;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLDocument;
import net.umllint.tool.model.xml.XMLElement;

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


public class XMIParser {


    public static UMLAbstractEntity parseUMLElement(XMLElement xmlElement) {

        XMLAttribute typeAttribute = xmlElement.getAttribute("xmi:type");

        if (typeAttribute == null) {
            System.out.print("-- NO TYPE FOR " + xmlElement.getName());
            return null;
        }

        String type = typeAttribute.getValue();

        if (type.equals("uml:Class")) {
            return parseUMLClass(xmlElement);
        } else if (type.equals("uml:Interface")) {
            return parseUMLInterface(xmlElement);
        } else if (type.equals("uml:Association")) {
            return parseUMLAssociation(xmlElement);
        } else if (type.equals("uml:Property")) {
            return parseUMLProperty(xmlElement);
        } else if (type.equals("uml:Package")) {
            return parseUMLPackage(xmlElement);
        } else if (type.equals("uml:LiteralString")) {
            return parseUMLLiteralString(xmlElement);
        } else if (type.equals("uml:Operation")) {
            return parseUMLOperation(xmlElement);
        } else if (type.equals("uml:Enumeration")) {
            return parseUMLEnumeration(xmlElement);
        } else if (type.equals("uml:Generalization")) {
            return parseUMLGeneralization(xmlElement);
        } else if (type.equals("uml:Model")) {
            return parseUMLModel(xmlElement);
        } else if (type.equals("uml:PrimitiveType")) {
            return parseUMLPrimitiveType(xmlElement);
        } else if (type.equals("uml:Parameter")) {
            return parseUMLParameter(xmlElement);
        } else if (type.equals("uml:Realization")) {
            return parseUMLRealization(xmlElement);
        } else {
            System.out.println(String.format("Unknown xmi:type %s", xmlElement.getAttribute("xmi:type")));
        }

        return null;
    }

    public static UMLGeneralization parseUMLGeneralization(XMLElement xmlElement) {
        UMLGeneralization umlElement = new UMLGeneralization();
        System.out.print("Generalization not supported right now, sorry!");
        return umlElement;
    }

    public static UMLRealization parseUMLRealization(XMLElement xmlElement) {

        //realization
        UMLRealization umlRealization = new UMLRealization();
        umlRealization.setName(xmlElement.getAttributeValue("name"));
        umlRealization.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlRealization.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlRealization.setClient(xmlElement.getAttributeValue("client"));
        umlRealization.setRealizingClassifier(xmlElement.getAttributeValue("realizingClassifier"));
        umlRealization.setSupplier(xmlElement.getAttributeValue("supplier"));

        System.out.println(String.format("\t[Realization] %s", umlRealization.getName()));
        UMLEntityDirectory.getInstance().setElement(umlRealization);

        return umlRealization;
    }

    public static UMLPrimitiveType parseUMLPrimitiveType(XMLElement xmlElement) {

        //primitive type
        UMLPrimitiveType umlPrimitiveType = new UMLPrimitiveType();
        umlPrimitiveType.setName(xmlElement.getAttributeValue("name"));
        umlPrimitiveType.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlPrimitiveType.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlPrimitiveType.setHref(xmlElement.getAttributeValue("href"));

        System.out.println(String.format("\t\t\t\t[PrimitiveType] %s", umlPrimitiveType.getName()));
        //UMLEntityDirectory.getInstance().setElement(umlPrimitiveType); //does not have id!

        return umlPrimitiveType;
    }

    public static UMLParameter parseUMLParameter(XMLElement xmlElement) {

        //parameter
        UMLParameter umlParameter = new UMLParameter();
        umlParameter.setName(xmlElement.getAttributeValue("name"));
        umlParameter.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlParameter.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlParameter.setIsOrdered(xmlElement.getAttributeValue("isOrdered"));
        umlParameter.setIsUnique(xmlElement.getAttributeValue("isUnique"));
        umlParameter.setKind(xmlElement.getAttributeValue("kind"));

        System.out.println(String.format("\t\t\t[Parameter] %s", umlParameter.getName()));
        UMLEntityDirectory.getInstance().setElement(umlParameter);

        //type
        XMLElement typeElement = xmlElement.getFirstElement("type");
        if (typeElement != null) {
            UMLPrimitiveType umlPrimitiveType = parseUMLPrimitiveType(typeElement);
            umlParameter.setType(umlPrimitiveType);
        }

        return umlParameter;
    }



    public static UMLAssociation parseUMLAssociation(XMLElement xmlElement) {
        UMLAssociation umlAssociation = new UMLAssociation();

        //operation
        umlAssociation.setName(xmlElement.getAttributeValue("name"));
        umlAssociation.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlAssociation.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlAssociation.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlAssociation.setIsDerived(xmlElement.getAttributeValue("isDerived"));
        umlAssociation.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));

        System.out.println(String.format("\t[Association] %s", umlAssociation.getName()));

        //properties
        List<UMLProperty> umlProperties = parseUMLProperties(xmlElement);
        umlAssociation.setProperties(umlProperties);

        UMLEntityDirectory.getInstance().setElement(umlAssociation);
        return umlAssociation;
    }

    public static List<UMLParameter> parseUMLParameters(XMLElement xmlElement) {

        List<UMLParameter> umlParameters = new LinkedList<UMLParameter>();

        for (XMLElement xmlElementProperty : xmlElement.getElements()) {

            String type = xmlElementProperty.getAttributeValue("xmi:type");
            if (type != null && type.equals("uml:Parameter")) {
                UMLParameter umlParameter = parseUMLParameter(xmlElementProperty);
                umlParameters.add(umlParameter);
                UMLEntityDirectory.getInstance().setElement(umlParameter);
            }
        }

        return umlParameters;
    }

    public static List<UMLProperty> parseUMLProperties(XMLElement xmlElement) {

        List<UMLProperty> umlProperties = new LinkedList<UMLProperty>();

        for (XMLElement xmlElementProperty : xmlElement.getElements()) {

            String type = xmlElementProperty.getAttributeValue("xmi:type");
            if (type != null && type.equals("uml:Property")) {
                UMLProperty umlProperty = parseUMLProperty(xmlElementProperty);
                umlProperties.add(umlProperty);
                UMLEntityDirectory.getInstance().setElement(umlProperty);
            }
        }

        return umlProperties;
    }

    public static List<UMLOperation> parseUMLClassOperations(XMLElement xmlElement) {

        List<UMLOperation> umlOperations = new LinkedList<UMLOperation>();

        for (XMLElement xmlElementProperty : xmlElement.getElements()) {

            String type = xmlElementProperty.getAttributeValue("xmi:type");
            if (type != null && type.equals("uml:Operation")) {
                UMLOperation umlOperation = parseUMLOperation(xmlElementProperty);
                umlOperations.add(umlOperation);
                UMLEntityDirectory.getInstance().setElement(umlOperation);
            }
        }

        return umlOperations;
    }


    public static UMLOperation parseUMLOperation(XMLElement xmlElement) {
        UMLOperation umlOperation = new UMLOperation();

        //operation
        umlOperation.setName(xmlElement.getAttributeValue("name"));
        umlOperation.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlOperation.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlOperation.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlOperation.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));
        umlOperation.setIsQuery(xmlElement.getAttributeValue("isQuery"));
        umlOperation.setOwnerScope(xmlElement.getAttributeValue("ownerScope"));
        umlOperation.setVisibility(xmlElement.getAttributeValue("visibility"));

        System.out.println(String.format("\t[Operation] %s", umlOperation.getName()));
        UMLEntityDirectory.getInstance().setElement(umlOperation);

        //parameters
        List<UMLParameter> umlParameters = parseUMLParameters(xmlElement);
        umlOperation.setParameters(umlParameters);

        return umlOperation;
    }

    public static UMLLiteralString parseUMLLiteralString(XMLElement xmlElement) {

        UMLLiteralString umlLiteralString = new UMLLiteralString();

        //literal string
        umlLiteralString.setName(xmlElement.getAttributeValue("name"));
        umlLiteralString.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlLiteralString.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlLiteralString.setValue(xmlElement.getAttributeValue("value"));
        System.out.println(String.format("\t\t\t[LiteralString] %s %s", xmlElement.getName(), umlLiteralString.getName()));

        UMLEntityDirectory.getInstance().setElement(umlLiteralString);
        return umlLiteralString;
    }


    public static UMLProperty parseUMLProperty(XMLElement xmlElement) {

        UMLProperty umlProperty = new UMLProperty();

        //property
        umlProperty.setName(xmlElement.getAttributeValue("name"));
        umlProperty.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlProperty.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlProperty.setVisibility(xmlElement.getAttributeValue("aggregation"));
        umlProperty.setIsAbstract(xmlElement.getAttributeValue("isDerived"));
        umlProperty.setIsActive(xmlElement.getAttributeValue("ownerScope"));
        umlProperty.setIsLeaf(xmlElement.getAttributeValue("visibility"));
        System.out.println(String.format("\t\t[Property] %s", umlProperty.getName()));

        //lower
        XMLElement lowerValueElement = xmlElement.getFirstElement("lowerValue");
        if (lowerValueElement != null) {
            UMLLiteralString umlLiteralStringLower = parseUMLLiteralString(lowerValueElement);
            umlProperty.setLowerValue(umlLiteralStringLower);
            UMLEntityDirectory.getInstance().setElement(umlLiteralStringLower);
        }

        //upper
        XMLElement upperValueElement = xmlElement.getFirstElement("upperValue");
        if (upperValueElement != null) {
            UMLLiteralString umlLiteralStringUpper = parseUMLLiteralString(upperValueElement);
            umlProperty.setUpperValue(umlLiteralStringUpper);
            UMLEntityDirectory.getInstance().setElement(umlLiteralStringUpper);
        }

        //default
        XMLElement defaultValueElement = xmlElement.getFirstElement("defaultValue");
        if (defaultValueElement != null) {
            UMLLiteralString umlLiteralStringDefault = parseUMLLiteralString(defaultValueElement);
            umlProperty.setDefaultValue(umlLiteralStringDefault);
            UMLEntityDirectory.getInstance().setElement(umlLiteralStringDefault);
        }

        UMLEntityDirectory.getInstance().setElement(umlProperty);
        return umlProperty;
    }

    public static UMLClass parseUMLClass(XMLElement xmlElement) {

        //class
        UMLClass umlClass = new UMLClass();
        umlClass.setName(xmlElement.getAttributeValue("name"));
        umlClass.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlClass.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlClass.setVisibility(xmlElement.getAttributeValue("visibility"));
        umlClass.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlClass.setIsActive(xmlElement.getAttributeValue("isActive"));
        umlClass.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));
        System.out.println(String.format("[Class] %s", umlClass.getName()));

        //properties
        List<UMLProperty> umlProperties = parseUMLProperties(xmlElement);
        umlClass.setProperties(umlProperties);

        //operations
        List<UMLOperation> umlOperations = parseUMLClassOperations(xmlElement);
        umlClass.setOperations(umlOperations);

        UMLEntityDirectory.getInstance().setElement(umlClass);
        return umlClass;
    }

    public static UMLEnumeration parseUMLEnumeration(XMLElement xmlElement) {

        //enumeration
        UMLEnumeration umlEnumeration = new UMLEnumeration();
        umlEnumeration.setName(xmlElement.getAttributeValue("name"));
        umlEnumeration.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlEnumeration.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlEnumeration.setVisibility(xmlElement.getAttributeValue("visibility"));
        umlEnumeration.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlEnumeration.setIsActive(xmlElement.getAttributeValue("isActive"));
        umlEnumeration.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));
        System.out.println("[Enumeration]");

        //properties
        List<UMLProperty> umlProperties = parseUMLProperties(xmlElement);
        umlEnumeration.setProperties(umlProperties);
        List<UMLOperation> umlOperations = parseUMLClassOperations(xmlElement);
        umlEnumeration.setOperations(umlOperations);

        UMLEntityDirectory.getInstance().setElement(umlEnumeration);
        return umlEnumeration;
    }

    public static UMLInterface parseUMLInterface(XMLElement xmlElement) {

        //interface
        UMLInterface umlInterface = new UMLInterface();
        umlInterface.setName(xmlElement.getAttributeValue("name"));
        umlInterface.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlInterface.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        umlInterface.setVisibility(xmlElement.getAttributeValue("visibility"));
        umlInterface.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlInterface.setIsActive(xmlElement.getAttributeValue("isActive"));
        umlInterface.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));
        System.out.println("[Interface]");

        //properties
        List<UMLProperty> umlProperties = parseUMLProperties(xmlElement);
        umlInterface.setProperties(umlProperties);
        List<UMLOperation> umlOperations = parseUMLClassOperations(xmlElement);
        umlInterface.setOperations(umlOperations);

        UMLEntityDirectory.getInstance().setElement(umlInterface);
        return umlInterface;
    }


    public static UMLPackage parseUMLPackage(XMLElement xmlElement) {

        //package
        UMLPackage umlPackage = new UMLPackage();
        umlPackage.setIsAbstract(xmlElement.getAttributeValue("isAbstract"));
        umlPackage.setIsLeaf(xmlElement.getAttributeValue("isLeaf"));
        umlPackage.setVisibility(xmlElement.getAttributeValue("visibility"));
        umlPackage.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        umlPackage.setXmiType(xmlElement.getAttributeValue("xmi:type"));
        System.out.println("\n[Package]");

        //elements
        for (XMLElement xmlElementChild : xmlElement.getElements()) {
            UMLAbstractEntity umlElement = parseUMLElement(xmlElementChild);
            if (umlElement != null) {
                umlPackage.addEntity(umlElement);
            }
        }

        System.out.println();
        UMLEntityDirectory.getInstance().setElement(umlPackage);
        return umlPackage;
    }

    public static UMLModel parseUMLModel(XMLElement xmlElement) {

        //model
        UMLModel umlModel = new UMLModel();
        umlModel.setName(xmlElement.getAttributeValue("name"));
        umlModel.setXmiId(xmlElement.getAttributeValue("xmi:id"));
        System.out.println(String.format("\n[Model] %s", umlModel.getName()));

        //elements
        for (XMLElement xmlElementChild : xmlElement.getElements()) {
            UMLAbstractEntity umlElement = parseUMLElement(xmlElementChild);
            if (umlElement != null) {
                umlModel.addEntity(umlElement);
            }
        }

        System.out.println();
        UMLEntityDirectory.getInstance().setElement(umlModel);
        return umlModel;
    }


    public static UMLDocument parseUMLDocument(XMLDocument xmlDocument) {

        //document
        UMLDocument umlDocument = new UMLDocument();
        System.out.println("[Document]");

        //presume we have root uml:Model and parse everyrthing inside
        XMLElement root = xmlDocument.getRoot();
        XMLElement model = root.child("uml:Model");

        //model
        UMLModel umlModel = parseUMLModel(model);
        umlDocument.setUmlModel(umlModel);

        System.out.println();
        return umlDocument;

    }


    public static UMLDocument parseUML(XMLDocument xmlDocument) {

        UMLDocument umlDocument = parseUMLDocument(xmlDocument);

        return umlDocument;
    }


    //helpers


    public static void printSUB(XMLElement xmlElement) {

        if (xmlElement.getElements().size() > 0) {
            System.out.println("--->");

            System.out.println("--->");
            for (XMLElement xmlElementChild : xmlElement.getElements()) {

                UMLAbstractEntity e = parseUMLElement(xmlElementChild);
            }
            System.out.println("<---");

        }

    }


}
