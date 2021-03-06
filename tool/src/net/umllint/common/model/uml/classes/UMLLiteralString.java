package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

public class UMLLiteralString extends UMLAbstractEntity {

    private String value;

    public UMLLiteralString() {
        setXmiType("uml:LiteralString");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public XMLElement toXML() {

        XMLElement xmlElement = super.toXML();
        xmlElement.addAttribute(new XMLAttribute("value", value));

        return xmlElement;
    }

}
