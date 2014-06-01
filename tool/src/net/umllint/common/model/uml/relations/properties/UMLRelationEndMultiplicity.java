package net.umllint.common.model.uml.relations.properties;


import net.umllint.common.model.uml.UMLAbstractEntity;

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
