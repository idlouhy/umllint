package net.umllint.tool.model.uml.relations;


import net.umllint.tool.model.uml.UMLAbstractEntity;
import net.umllint.tool.model.uml.classes.UMLProperty;
import net.umllint.tool.model.uml.relations.properties.UMLRelationEnd;
import net.umllint.tool.model.xml.XMLAttribute;
import net.umllint.tool.model.xml.XMLElement;

import java.util.List;

public abstract class UMLAbstractRelation extends UMLAbstractEntity {

    private String isAbstract;
    private String isDerived;
    private String isLeaf;

    @Deprecated
    private UMLRelationEnd relationEnd1;
    @Deprecated
    private UMLRelationEnd relationEnd2;

    private List<UMLProperty> properties;

    //auto

    public String getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(String isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getIsDerived() {
        return isDerived;
    }

    public void setIsDerived(String isDerived) {
        this.isDerived = isDerived;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public UMLRelationEnd getRelationEnd1() {
        return relationEnd1;
    }

    public void setRelationEnd1(UMLRelationEnd relationEnd1) {
        this.relationEnd1 = relationEnd1;
    }

    public UMLRelationEnd getRelationEnd2() {
        return relationEnd2;
    }

    public void setRelationEnd2(UMLRelationEnd relationEnd2) {
        this.relationEnd2 = relationEnd2;
    }

    public List<UMLProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UMLProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {

        StringBuilder relationEnds = new StringBuilder();


        if (getRelationEnd1() != null) {
            relationEnds.append(String.format("\t\t\t"));
            relationEnds.append(getRelationEnd1().toString());
            relationEnds.append(String.format("%n"));
        }

        if (getRelationEnd2() != null) {
            relationEnds.append(String.format("\t\t\t"));
            relationEnds.append(getRelationEnd2().toString());
            relationEnds.append(String.format("%n"));
        }

        return String.format("[%s] name=%s id=%s %n%s", getClass().getSimpleName(), getName(), getXmiId(), relationEnds.toString());
    }

    @Override
    public XMLElement toXML() {

        XMLElement xmlElement = super.toXML();
        xmlElement.addAttribute(new XMLAttribute("isAbstract", isAbstract));
        xmlElement.addAttribute(new XMLAttribute("isDerived", isDerived));
        xmlElement.addAttribute(new XMLAttribute("isLeaf", isLeaf));

        //properties
        for (UMLProperty property : properties) {
            XMLElement xmlElementProperty = property.toXML();
            xmlElementProperty.setName("ownedEnd");
            xmlElement.addElement(xmlElementProperty);
        }

        return xmlElement;
    }

}
