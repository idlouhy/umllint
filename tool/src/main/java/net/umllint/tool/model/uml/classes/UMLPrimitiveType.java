package net.umllint.tool.model.uml.classes;


import net.umllint.tool.model.uml.UMLAbstractEntity;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLElement;

public class UMLPrimitiveType extends UMLAbstractEntity {

    private String href;

    public UMLPrimitiveType() {
        setXmiType("uml:PrimitiveType");
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("xmi:type", getXmiType()));
        return element;
    }

}
