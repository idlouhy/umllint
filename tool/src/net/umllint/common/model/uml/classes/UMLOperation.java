package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

import java.util.LinkedList;
import java.util.List;

public class UMLOperation extends UMLAbstractEntity {

    private String isAbstract;
    private String isLeaf;
    private String isQuery;
    private String ownerScope;
    private String visibility;

    private List<UMLParameter> parameters = new LinkedList<UMLParameter>();

    public UMLOperation() {
        setXmiType("uml:Operation");
    }

    public String getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(String isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getOwnerScope() {
        return ownerScope;
    }

    public void setOwnerScope(String ownerScope) {
        this.ownerScope = ownerScope;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<UMLParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<UMLParameter> parameters) {
        this.parameters = parameters;
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("xmi:type", getXmiType()));
        return element;
    }

}
