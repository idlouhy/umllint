package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.classes.common.UMLAbstractClass;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

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
