package net.umllint.common.model.uml;

import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLPackage extends UMLContainer {

    private String isAbstract;
    private String isLeaf;
    private String visibility;

    public UMLPackage() {
        setXmiType("uml:Package");
    }

    //generated

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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    //export
    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.addAttribute(new XMLAttribute("isAbstract", isAbstract));
        element.addAttribute(new XMLAttribute("isLeaf", isLeaf));
        element.addAttribute(new XMLAttribute("visibility", visibility));
        return element;
    }


}
