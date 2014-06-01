package net.umllint.common.model.uml;

import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public abstract class UMLAbstractEntity {

    private String name;

    private String xmiId;
    private String xmiType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXmiId() {
        return xmiId;
    }

    public void setXmiId(String xmiId) {
        this.xmiId = xmiId;
    }

    public String getXmiType() {
        return xmiType;
    }

    public void setXmiType(String xmiType) {
        this.xmiType = xmiType;
    }

    public XMLElement toXML() {
        XMLElement element = new XMLElement("ownedMember");
        element.addAttribute(new XMLAttribute("name", name));
        element.addAttribute(new XMLAttribute("xmi:id", xmiId));
        element.addAttribute(new XMLAttribute("xmi:type", xmiType));
        return element;
    }
}
