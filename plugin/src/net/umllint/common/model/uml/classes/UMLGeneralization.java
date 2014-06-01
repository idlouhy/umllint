package net.umllint.common.model.uml.classes;

import net.umllint.common.model.uml.UMLEntityDirectory;
import net.umllint.common.model.uml.classes.common.UMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLGeneralization extends UMLElement {

    private String general;

    public UMLGeneralization() {
        setXmiType("uml:Generalization");
    }

    public String getGeneral() {
        return UMLEntityDirectory.resolveXmiId(general);
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    @Override
    public String toString() {
        return String.format("[%s] name=%s id=%s general=%s", getClass().getSimpleName(), getName(), getXmiId(), getGeneral());
    }
}
