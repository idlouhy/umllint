package net.umllint.common.model.uml.classes.common;


import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.uml.classes.UMLGeneralization;
import net.umllint.common.model.uml.classes.UMLOperation;
import net.umllint.common.model.uml.classes.UMLProperty;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

import java.util.LinkedList;
import java.util.List;

public class UMLAbstractClass extends UMLAbstractEntity {

    private String isAbstract; //true/false
    private String isActive; //true/false
    private String isLeaf; //true/false

    private String visibility; //public

    private UMLGeneralization generalization = null;

    private List<UMLProperty> properties = new LinkedList<UMLProperty>();
    private List<UMLOperation> operations = new LinkedList<UMLOperation>();


    public String getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(String isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public UMLGeneralization getGeneralization() {
        return generalization;
    }

    public void setGeneralization(UMLGeneralization generalization) {
        this.generalization = generalization;
    }

    public List<UMLProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UMLProperty> properties) {
        this.properties = properties;
    }

    public List<UMLOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<UMLOperation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        if (getGeneralization() != null) {
            s.append(String.format("\t\t\t%s%n", getGeneralization()));
        }

        return String.format("[%s] name=%s id=%s%s", getClass().getSimpleName(), getName(), getXmiId(), s.toString());
    }

    public XMLElement toXML() {

        XMLElement xmlElement = super.toXML();
        xmlElement.addAttribute(new XMLAttribute("isAbstract", isAbstract));
        xmlElement.addAttribute(new XMLAttribute("isActive", isActive));
        xmlElement.addAttribute(new XMLAttribute("isLeaf", isLeaf));
        xmlElement.addAttribute(new XMLAttribute("visibility", visibility));

        //generalization
        for (UMLProperty property : properties) {
            XMLElement xmlElementChild = property.toXML();
            xmlElementChild.setName("ownedAttribute");
            xmlElement.addElement(xmlElementChild);
        }


        //properties
        if (generalization != null) {
            XMLElement xmlElementChild = generalization.toXML();
            xmlElementChild.setName("generalization");
            xmlElement.addElement(xmlElementChild);
        }

        //operations
        for (UMLOperation operation : operations) {
            XMLElement xmlElementChild = operation.toXML();
            xmlElementChild.setName("ownedOperation");
            xmlElement.addElement(xmlElementChild);
        }

        return xmlElement;
    }
}
