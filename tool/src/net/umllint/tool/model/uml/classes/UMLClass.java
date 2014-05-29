package net.umllint.tool.model.uml.classes;


import net.umllint.tool.model.uml.classes.common.UMLElement;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLElement;

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
