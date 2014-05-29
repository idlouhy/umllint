package net.umllint.tool.model.uml.classes;


import net.umllint.tool.model.uml.classes.common.UMLAbstractClass;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLElement;

public class UMLInterface extends UMLAbstractClass {

    public UMLInterface() {
        setXmiType("uml:Interface");
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("xmi:type", getXmiType()));
        return element;
    }

}
