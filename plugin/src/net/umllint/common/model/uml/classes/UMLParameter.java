package net.umllint.common.model.uml.classes;

import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLParameter extends UMLAbstractEntity {

    private String isOrdered;
    private String isUnique;
    private String kind;

    private UMLPrimitiveType type;

    public UMLParameter() {
        setXmiType("uml:Parameter");
    }

    public String getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(String isOrdered) {
        this.isOrdered = isOrdered;
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public UMLPrimitiveType getType() {
        return type;
    }

    public void setType(UMLPrimitiveType type) {
        this.type = type;
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("xmi:type", getXmiType()));
        return element;
    }

}
