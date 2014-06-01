package net.umllint.common.model.uml.relations.properties;

import net.umllint.common.model.uml.UMLAbstractEntity;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLRelationEndMultiplicity extends UMLAbstractEntity {

    private String value;

    public UMLRelationEndMultiplicity() {
        this.value = value;
        setXmiType("uml:LiteralString");
    }

    @Override
    public String toString() {
        return String.format(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
