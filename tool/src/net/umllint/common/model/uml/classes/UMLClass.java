package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.classes.common.UMLElement;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

public class UMLClass extends UMLElement {

    public UMLClass() {
        setXmiType("uml:Class");
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("xmi:type", getXmiType()));
        return element;
    }

}
