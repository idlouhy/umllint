package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

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
